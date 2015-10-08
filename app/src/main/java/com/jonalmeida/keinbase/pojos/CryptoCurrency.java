package com.jonalmeida.keinbase.pojos;

import java.util.List;

public class CryptoCurrency {
//"cryptocurrency_addresses": {
//    "bitcoin": [{
//        "address": "1Nwrzm3HiirdhuCwZoCpiv8bdziL3wZTyL",
//        "sig_id": "94263fd751aeee2b4d6f2a523cba9553994daad0c1fae433c0bee956f494f3b50f"
//    }]
//}
    private List<Bitcoin> bitcoin;

    public List<Bitcoin> getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(List<Bitcoin> bitcoin) {
        this.bitcoin = bitcoin;
    }
}
