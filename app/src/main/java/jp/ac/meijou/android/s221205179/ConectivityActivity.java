package jp.ac.meijou.android.s221205179;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s221205179.databinding.ConectivityBinding;

public class ConectivityActivity extends AppCompatActivity {

    private ConectivityBinding binding;

    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ConectivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager=getSystemService(ConnectivityManager.class);
        var currentNetwork=connectivityManager.getActiveNetwork();
        upDateTransport(currentNetwork);
        upDateIpAddress(currentNetwork);
    }

    private void upDateTransport(Network network){
        var caps=connectivityManager.getNetworkCapabilities(network);
        
        if (caps!=null){
            String transport;
            if(caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                transport="モバイル通信";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport="wifi";
            }else {
                transport="その他";
            }
            binding.transportName.setText(transport);
        }
    }
    private void upDateIpAddress(Network network){
        var linkProps=connectivityManager.getLinkProperties(network);
        if(linkProps!=null){
            var address=linkProps.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));
            binding.inAddressName.setText(address);
        }
    }
}