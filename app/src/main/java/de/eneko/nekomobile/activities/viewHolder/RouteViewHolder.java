package de.eneko.nekomobile.activities.viewHolder;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.RouteModel;
import de.eneko.nekomobile.beans.Route;

public class RouteViewHolder extends BaseViewHolder{
    private TextView txtvRouteName = null;
    private TextView txtvDatum = null;
    private TextView txtvErstellDatum = null;

    public RouteViewHolder(View pView, Basemodel pModel) {
        super(pView, pModel);
    }

    @Override
    public RouteModel getBasemodel() {
        return (RouteModel) super.getBasemodel();
    }

    @Override
    public void updateView() {
        setTxtvRouteName(mView.findViewById(R.id.txtvRouteName));
        setTxtvDatum(mView.findViewById(R.id.txtvDatum));
        setTxtvErstellDatum(mView.findViewById(R.id.txtvErstelldatum));
        getTxtvRouteName().setText(getBasemodel().getBezeichnung());
        getTxtvDatum().setText(new SimpleDateFormat(GlobalConst.dateFormat).format(getBasemodel().getDatum()));
        getTxtvErstellDatum().setText(getBasemodel().getCreateTimestamp());
    }

    // region controls

    public TextView getTxtvRouteName() {
        return txtvRouteName;
    }

    public void setTxtvRouteName(TextView txtvRouteName) {
        this.txtvRouteName = txtvRouteName;
    }

    public TextView getTxtvDatum() {
        return txtvDatum;
    }

    public void setTxtvDatum(TextView txtvDatum) {
        this.txtvDatum = txtvDatum;
    }

    public TextView getTxtvErstellDatum() {
        return txtvErstellDatum;
    }

    public void setTxtvErstellDatum(TextView txtvErstellDatum) {
        this.txtvErstellDatum = txtvErstellDatum;
    }
// endregion controls

}
