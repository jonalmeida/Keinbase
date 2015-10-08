package com.jonalmeida.keinbase.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class User {
    //    {
    //        "id":             "55c079bf5f1650fc1361a1c3a4709900",
    //        "basics":                     { },
    //        "invitation_stats":           { },
    //        "profile":                    null,
    //        "emails":                     { },
    //        "billing_and_quotas":         { }
    //        "public_keys":                { },
    //        "private_keys":               { },
    //        "proofs_summary":             { },
    //        "cryptocurrency_addresses":   { },
    //        "pictures":                   { },
    //        "sigs":                       { },
    //        "devices":                    { },
    //    }
    private String id;
    private Basics basics;
    private Map<String, Object> invitation_stats;
    private Profile profile;
    private Map<String, Object> emails;
    private Map<String, Object> billing_and_quotes;
    private Map<String, Object> public_keys;
    private Map<String, Object> private_keys;
    private Map<String, Object> proofs_summary;
    private CryptoCurrency cryptocurrency_addresses;
    private Pictures pictures;
    private Map<String, Object> sigs;
    private Map<String, Object> devices;

    public static final String ID                       = "id";
    public static final String BASICS                   = "basics";
    public static final String INVITATION_STATS         = "invitation_stats";
    public static final String PROFILE                  = "profile";
    public static final String EMAILS                   = "emails";
    public static final String BILLING_AND_QUOTES       = "billing_and_quotes";
    public static final String PUBLIC_KEYS              = "public_keys";
    public static final String PRIVATE_KEYS             = "private_keys";
    public static final String PROOFS_SUMMARY           = "proofs_summary";
    public static final String CRYPTOCURRENCY_ADDRESSES = "cryptocurrency_addresses";
    public static final String PICTURES                 = "pictures";
    public static final String SIGS                     = "sigs";
    public static final String DEVICES                  = "devices";


    public Map<String, Object> getInvitation_stats() {
        return invitation_stats;
    }

    public void setInvitation_stats(Map<String, Object> invitation_stats) {
        this.invitation_stats = invitation_stats;
    }

    public Map<String, Object> getEmails() {
        return emails;
    }

    public void setEmails(Map<String, Object> emails) {
        this.emails = emails;
    }

    public Map<String, Object> getPublic_keys() {
        return public_keys;
    }

    public void setPublic_keys(Map<String, Object> public_keys) {
        this.public_keys = public_keys;
    }

    public Map<String, Object> getPrivate_keys() {
        return private_keys;
    }

    public void setPrivate_keys(Map<String, Object> private_keys) {
        this.private_keys = private_keys;
    }

    public Map<String, Object> getBilling_and_quotes() {
        return billing_and_quotes;
    }

    public void setBilling_and_quotes(Map<String, Object> billing_and_quotes) {
        this.billing_and_quotes = billing_and_quotes;
    }

    public Map<String, Object> getProofs_summary() {
        return proofs_summary;
    }

    public void setProofs_summary(Map<String, Object> proofs_summary) {
        this.proofs_summary = proofs_summary;
    }

    public Map<String, Object> getSigs() {
        return sigs;
    }

    public void setSigs(Map<String, Object> sigs) {
        this.sigs = sigs;
    }

    public Map<String, Object> getDevices() {
        return devices;
    }

    public void setDevices(Map<String, Object> devices) {
        this.devices = devices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Basics getBasics() {
        return basics;
    }

    public void setBasics(Basics basics) {
        this.basics = basics;
    }

    public Pictures getPictures() {
        return pictures;
    }

    public void setPictures(Pictures pictures) {
        this.pictures = pictures;
    }

    public CryptoCurrency getCryptocurrency_addresses() {
        return cryptocurrency_addresses;
    }

    public void setCryptocurrency_addresses(CryptoCurrency cryptocurrency_addresses) {
        this.cryptocurrency_addresses = cryptocurrency_addresses;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
