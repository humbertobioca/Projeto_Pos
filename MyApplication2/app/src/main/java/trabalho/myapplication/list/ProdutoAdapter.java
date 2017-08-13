package trabalho.myapplication.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import trabalho.myapplication.R;


/**
 * Created by Humberto on 12/08/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produto> {
    public ProdutoAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);}
@NonNull
@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto obj = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.adp_produto, parent, false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNome);
        TextView txtAno = (TextView) convertView.findViewById(R.id.txtPreco);

        txtNome.setText(obj.nome);
        txtAno.setText(obj.ano);

        Picasso.with(getContext())
                .load(obj.urlImg)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(img);

        return convertView;
    }
}


