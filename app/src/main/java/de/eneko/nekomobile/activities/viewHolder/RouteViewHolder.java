package de.eneko.nekomobile.activities.viewHolder;

import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Route;

public class RouteViewHolder extends BaseViewHolder{
    private TextView txtvRouteName = null;
    private TextView txtvDatum = null;
    private TextView txtvErstellDatum = null;

    public RouteViewHolder(View pView, Object pBean) {
        super(pView, pBean);
    }

    @Override
    public Route getBean() {
        return (Route) super.getBean();
    }

    @Override
    public void updateView() {

        setTxtvRouteName(mView.findViewById(R.id.txtvRouteName));
        setTxtvDatum(mView.findViewById(R.id.txtvDatum));
        setTxtvErstellDatum(mView.findViewById(R.id.txtvErstelldatum));
        getTxtvRouteName().setText(getBean().getBezeichnung());
        getTxtvDatum().setText(new SimpleDateFormat(GlobalConst.dateFormat).format(getBean().getDatum()));
        getTxtvErstellDatum().setText(getBean().getCreateTimestamp());
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
