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

import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.list.LiegenschaftListActivity;
import de.eneko.nekomobile.activities.viewHolder.Liegenschaft.LiegenschaftDetailViewHolder;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.SontexFileHandler;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LiegenschaftActivity extends AppCompatActivity{



    protected LiegenschaftDetailViewHolder viewHolder = null;

    protected ArrayList<Messgeraet> Liste = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_liegenschaft);
        Liegenschaft liegenschaft = CurrentObjectNavigation.getInstance().getLiegenschaft();
        viewHolder = new LiegenschaftDetailViewHolder( null,liegenschaft.getBaseModel(), this);
        viewHolder.updateView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_base_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                viewHolder.save();
                exit();
                return true;
            case R.id.menu_item_bewertung:
                viewHolder.save();

                String myResponse = "";

                OkHttpClient client = new OkHttpClient();
                String url = "https://exchange-platform.app/api/project/v1.0/project/391";

                Request request = new Request.Builder()
                        .url(url)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZjJkMmE0Zi0yZGJmLTQwYjMtODljZi05MWRlYzI3ZTViZDkifQ.eyJpYXQiOjE2Njk5Njc4ODMsImp0aSI6IjVmY2M1YjdiLTA5NWEtNGRlMy1hNWI2LTgwOTlmMjIxZDU4MCIsImlzcyI6Imh0dHBzOi8vc3NvLmV4Y2hhbmdlLXBsYXRmb3JtLmFwcC9hdXRoL3JlYWxtcy9zb25leGEiLCJhdWQiOiJodHRwczovL3Nzby5leGNoYW5nZS1wbGF0Zm9ybS5hcHAvYXV0aC9yZWFsbXMvc29uZXhhIiwic3ViIjoiOTJmMzM3MTQtNzEyMS00OTZjLWEwNTMtZjE5NzhiZGJjY2ZiIiwidHlwIjoiT2ZmbGluZSIsImF6cCI6Im5naW54LWFwaSIsInNlc3Npb25fc3RhdGUiOiIxNDczMzRkNy1hMzU3LTRmZjctODhkOC1mM2UxNGU1MGViYTIiLCJzY29wZSI6ImVtYWlsIHByb2ZpbGUgb2ZmbGluZV9hY2Nlc3MiLCJzaWQiOiIxNDczMzRkNy1hMzU3LTRmZjctODhkOC1mM2UxNGU1MGViYTIifQ.Uoe_GI38sAucGSpMCt0H3g-x5dNPFeDKcmAZ6HSTIGU")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        String ddd = e.toString();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String myResponse = response.body().string();



                        LiegenschaftActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run()    {




                                viewHolder.getEtNotizMitarbeiter().setText(myResponse);
                            }
                        });

                    }
                });




                return true;
            case R.id.menu_item_parametrieren:
                // anlegen der geräte
                Liste = new ArrayList<>();
                Liste.addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getDoneMessgeraets().stream()
                        .filter(m -> m.getSonexaId() == -1)
                        .collect(Collectors.toList())
                );

                if (Liste.stream().count() == 0){
                    return false;
                }

                client = new OkHttpClient();
                //Auslesedatei format xml
                String xmlContent = SontexFileHandler.getInstance().createSonexaRoad(viewHolder.getActivity(),Liste);
                RequestBody body = RequestBody.create(MediaType.parse("application/xml"),xmlContent);

                // Project Id
                Liegenschaft liegenschaft = CurrentObjectNavigation.getInstance().getLiegenschaft();
                url = "https://exchange-platform.app/api/project/v1.0/project/" + liegenschaft.getSonexaProjectId() + "/import";


                request = new Request.Builder()
                        .url(url)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZjJkMmE0Zi0yZGJmLTQwYjMtODljZi05MWRlYzI3ZTViZDkifQ.eyJpYXQiOjE2Njk5Njc4ODMsImp0aSI6IjVmY2M1YjdiLTA5NWEtNGRlMy1hNWI2LTgwOTlmMjIxZDU4MCIsImlzcyI6Imh0dHBzOi8vc3NvLmV4Y2hhbmdlLXBsYXRmb3JtLmFwcC9hdXRoL3JlYWxtcy9zb25leGEiLCJhdWQiOiJodHRwczovL3Nzby5leGNoYW5nZS1wbGF0Zm9ybS5hcHAvYXV0aC9yZWFsbXMvc29uZXhhIiwic3ViIjoiOTJmMzM3MTQtNzEyMS00OTZjLWEwNTMtZjE5NzhiZGJjY2ZiIiwidHlwIjoiT2ZmbGluZSIsImF6cCI6Im5naW54LWFwaSIsInNlc3Npb25fc3RhdGUiOiIxNDczMzRkNy1hMzU3LTRmZjctODhkOC1mM2UxNGU1MGViYTIiLCJzY29wZSI6ImVtYWlsIHByb2ZpbGUgb2ZmbGluZV9hY2Nlc3MiLCJzaWQiOiIxNDczMzRkNy1hMzU3LTRmZjctODhkOC1mM2UxNGU1MGViYTIifQ.Uoe_GI38sAucGSpMCt0H3g-x5dNPFeDKcmAZ6HSTIGU")
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
                                Messgeraet mg = iList.get(0);
                                mg.setSonexaId(Integer.parseInt(id));

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        LiegenschaftActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run()    {
                                // viewHolder.getEtNotizMitarbeiter().setText(myResponse);
                            }
                        });
                    }
                });
          return true;
        }
        return false;
    }


    protected void exit(){
        Intent intent = new Intent(this, LiegenschaftListActivity.class);
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
