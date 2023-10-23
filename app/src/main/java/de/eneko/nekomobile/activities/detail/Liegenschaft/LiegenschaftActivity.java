package de.eneko.nekomobile.activities.detail.Liegenschaft;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.*;

import org.json.*;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.list.LiegenschaftListActivity;
import de.eneko.nekomobile.activities.list.MessgeraetSonexaListActivity;
import de.eneko.nekomobile.activities.viewHolder.Liegenschaft.LiegenschaftDetailViewHolder;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.QundisFileHandler;
import de.eneko.nekomobile.controllers.SontexFileHandler;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LiegenschaftActivity extends AppCompatActivity{

    protected Liegenschaft liegenschaft = null;

    protected LiegenschaftDetailViewHolder viewHolder = null;

    protected ArrayList<Messgeraet> Liste = new ArrayList<>();

    protected MenuItem menuItemSonexaImport = null;
    protected MenuItem menuItemSonexaExport = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_liegenschaft);
        liegenschaft = CurrentObjectNavigation.getInstance().getLiegenschaft();
        viewHolder = new LiegenschaftDetailViewHolder( null,liegenschaft.getBaseModel(), this);
        viewHolder.updateView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_liegenschaft_menu, menu);
        menuItemSonexaImport = menu.findItem(R.id.menu_item_sonexaImport);
        menuItemSonexaExport = menu.findItem(R.id.menu_item_sonexaExport);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                viewHolder.save();
                exit();
                return true;
            case R.id.menu_item_sonexaImport:
                viewHolder.save();
                String myResponse = "";
                OkHttpClient client = new OkHttpClient();
                // Project Id
                String url = "https://exchange-platform.app/api/measurements/v1.0//project/" + liegenschaft.getSonexaProjectHash() +".json";
                Request request = new Request.Builder()
                        .url(url)
                        .header("Authorization", GlobalConst.SONTEX_TOKEN)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        String ddd = e.toString();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String myResponse = response.body().string();
                        liegenschaft.setSonexaReadOut(myResponse);

                        String jsonString = myResponse ;
                        try {
                            JSONObject obj = new JSONObject(jsonString);

                            JSONArray arrMessgeraete = obj.getJSONArray("devices");
                            for (int i = 0; i < arrMessgeraete.length(); i++)
                            {

                                String funknummer = arrMessgeraete.getJSONObject(i).getString("identifier");

                                //suchen des Messgeräts: neueNnummer oder neueFunkNummer muss mit dem identifier übereinstimmen
                                List<Messgeraet> importedMessgeraete = liegenschaft.getMessgeraets() .stream()
                                        .filter(m -> m.getNeueFunkNummer().equals(funknummer) || m.getNeueNummer().equals(funknummer))
                                        .collect(Collectors.toList());

                                // setzen des Sonexa rssi zu Messgerät
                                if (importedMessgeraete.size() > 0){
                                    Messgeraet mg = importedMessgeraete.get(0);
                                    JSONObject device = arrMessgeraete.getJSONObject(i);
                                    JSONArray arrMeasurments = arrMessgeraete.getJSONObject(i).getJSONArray("measurements");
                                   if (arrMeasurments.length() > 0){
                                        JSONObject   records = arrMeasurments.getJSONObject(0).getJSONObject("records");
                                        Integer rssi = -1;
                                        rssi = records.getInt("APP_RSSI-0-0-0-0");
                                        mg.setSonexaRSSI(java.lang.Math.abs(rssi));
                                   }
                            }

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                        LiegenschaftActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run()    {
                                menuItemSonexaExport.setIcon(R.drawable.icon_export_sonexa_ok);
                                showSontexNewReadout();
                            }
                        });

                    }
                });
                return true;
            case R.id.menu_item_sonexaExport:
                // anlegen der geräte
                Liste = new ArrayList<>();
                Liste.addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getDoneMessgeraets().stream()
                        .filter(m -> m.getSonexaId() == -1)
                        .collect(Collectors.toList())
                );
                Liste = new ArrayList<>();

                ArrayList Liste_RWM = new ArrayList<>();
                Liste_RWM.addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getRauchmelder().stream()
                        .collect(Collectors.toList())
                );

                if (Liste.stream().count() == 0 && Liste_RWM.stream().count() == 0 ){
                    return false;
                }

                client = new OkHttpClient();
                //Auslesedatei format xml
                String xmlContent = SontexFileHandler.getInstance().createSonexaRoad(viewHolder.getActivity(),Liste, Liste_RWM);
                RequestBody body = RequestBody.create(MediaType.parse("application/xml"),xmlContent);

                // Project Id
                url = "https://exchange-platform.app/api/project/v1.0/project/" + liegenschaft.getSonexaProjectId() + "/import";

                request = new Request.Builder()
                        .url(url)
                        .header("Authorization", GlobalConst.SONTEX_TOKEN)
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        String ddd = e.toString();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String myResponse = response.body().string();
                        Log.d("STATE", myResponse);
                        String jsonString = myResponse ;
                        try {
                            JSONObject obj = new JSONObject(jsonString);
                            JSONArray arr = obj.getJSONObject("importSummary").getJSONArray("importedDevices");
                            for (int i = 0; i < arr.length(); i++)
                            {

                                String id = arr.getJSONObject(i).getString("id");
                                String identifier = arr.getJSONObject(i).getString("identifier");
                                String radioNumber = identifier.replaceFirst("SON:","");

                                //suchen des Messgeräts: neueNnummer oder neueFunkNummer muss mit dem identifier übereinstimmen
                                List<Messgeraet> iList = Liste.stream()
                                        .filter(m -> m.getNeueFunkNummer().equals(radioNumber) || m.getNeueNummer().equals(radioNumber))
                                        .collect(Collectors.toList());

                                // setzen des Sonexa Id zu Messgerät
                                if(iList.size() > 0){
                                    Messgeraet mg = iList.get(0);
                                    mg.setSonexaId(Integer.parseInt(id));
                                }

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        LiegenschaftActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run()    {
                                menuItemSonexaExport.setIcon(R.drawable.icon_export_sonexa_ok);
                            }
                        });
                    }
                });
          return true;


        case R.id.menu_item_QundisPltExport:
            viewHolder.save();
            ArrayList<Messgeraet> messg = new ArrayList<Messgeraet>();
            QundisFileHandler.getInstance().exportQundisPltFile(viewHolder.getActivity(),viewHolder.getBasemodel().getBean());
            exit();
        }
        return false;
    }


    protected void exit(){
        Intent intent = new Intent(this, LiegenschaftListActivity.class);
        startActivity(intent);
    }

    protected void showSontexNewReadout(){
        Intent intent = new Intent(this, MessgeraetSonexaListActivity.class);
        startActivity(intent);
    }


    public void onBackPressed(){
        exit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (viewHolder != null){
            viewHolder.updateView();
        }
    }
}
