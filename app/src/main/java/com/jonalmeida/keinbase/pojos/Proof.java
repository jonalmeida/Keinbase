package com.jonalmeida.keinbase.pojos;

public class Proof {
//{
//    "proof_type": "twitter",
//    "nametag": "jonalmeida_",
//    "state": 1,
//    "proof_url": "https://twitter.com/jonalmeida_/status/460954657394008064",
//    "sig_id": "331b7cd7523b0e83c58cdf29963361082a320937dd18ea0196119b226148cc960f",
//    "proof_id": "bd56015690e0ccf46291e710",
//    "human_url": "https://twitter.com/jonalmeida_/status/460954657394008064",
//    "service_url": "https://twitter.com/jonalmeida_",
//    "presentation_group": "twitter",
//    "presentation_tag": "tweet"
//}
    private String proof_type;
    private String nametag;
    private int state;
    private String proof_url;
    private String sig_id;
    private String human_url;
    private String service_url;
    private String presentation_group;
    private String presentation_tag;

    public String getProof_type() {
        return proof_type;
    }

    public void setProof_type(String proof_type) {
        this.proof_type = proof_type;
    }

    public String getNametag() {
        return nametag;
    }

    public void setNametag(String nametag) {
        this.nametag = nametag;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getProof_url() {
        return proof_url;
    }

    public void setProof_url(String proof_url) {
        this.proof_url = proof_url;
    }

    public String getSig_id() {
        return sig_id;
    }

    public void setSig_id(String sig_id) {
        this.sig_id = sig_id;
    }

    public String getHuman_url() {
        return human_url;
    }

    public void setHuman_url(String human_url) {
        this.human_url = human_url;
    }

    public String getService_url() {
        return service_url;
    }

    public void setService_url(String service_url) {
        this.service_url = service_url;
    }

    public String getPresentation_group() {
        return presentation_group;
    }

    public void setPresentation_group(String presentation_group) {
        this.presentation_group = presentation_group;
    }

    public String getPresentation_tag() {
        return presentation_tag;
    }

    public void setPresentation_tag(String presentation_tag) {
        this.presentation_tag = presentation_tag;
    }
}
