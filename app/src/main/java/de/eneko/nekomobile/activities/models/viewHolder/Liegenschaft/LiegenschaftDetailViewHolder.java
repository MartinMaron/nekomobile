package de.eneko.nekomobile.activities.models.viewHolder.Liegenschaft;


import android.app.Activity;
import android.view.View;

import de.eneko.nekomobile.Misc;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.controllers.PhotoHandler;

public class LiegenschaftDetailViewHolder extends LiegenschaftBaseViewHolder {

    public LiegenschaftDetailViewHolder(View pView, Basemodel pModel, Activity pActivity) {
        super(pView, pModel,pActivity);
    }

   @Override
   public void updateView() {

       setTxtvLiegenschaft(findViewById(R.id.tvDisplay));
       setEtNotizMitarbeiter(findViewById(R.id.etBemerkung));

       setIvPhoto(findViewById(R.id.ivPhoto));
       if(getIvPhoto() != null) {
           getIvPhoto().setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String relativePath = getBasemodel().getBean().getAdresseOneLine();
                   relativePath = relativePath + "@" + Misc.getDateAsString();
                   String filename = "#" + getBasemodel().getBean().getNekoId() + "@";
                   PhotoHandler.getInstance().openCameraIntent(relativePath,filename,getActivity());
               }
           });
       }
       loadData();
    }


    protected void loadData(){
        getTxtvLiegenschaft().setText(getBasemodel().getBemerkung());
        getEtNotizMitarbeiter().setText(getBasemodel().getNotizMitarbeiter());
    }

    public void save(){
        getBasemodel().setNotizMitarbeiter(getEtNotizMitarbeiter().getText().toString());
        getBasemodel().save();
    }



}
