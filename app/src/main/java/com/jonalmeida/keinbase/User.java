package com.jonalmeida.keinbase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.LinkedHashMap;
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
    private String id = "id";
    private Basics basics = new Basics();
    private Map<String, Object> invitation_stats = new LinkedHashMap<>();
    private Map<String, Object> profile = new LinkedHashMap<>();
    private Map<String, Object> emails = new LinkedHashMap<>();
    private Map<String, Object> billing_and_quotes = new LinkedHashMap<>();
    private Map<String, Object> public_keys = new LinkedHashMap<>();
    private Map<String, Object> private_keys = new LinkedHashMap<>();
    private Map<String, Object> proofs_summary = new LinkedHashMap<>();
    private Map<String, Object> cryptocurrency_addresses = new LinkedHashMap<>();
    private Pictures pictures = new Pictures();
    private Map<String, Object> sigs = new LinkedHashMap<>();
    private Map<String, Object> devices = new LinkedHashMap<>();

    public Map<String, Object> getInvitation_stats() {
        return invitation_stats;
    }

    public void setInvitation_stats(Map<String, Object> invitation_stats) {
        this.invitation_stats = invitation_stats;
    }

    public Map<String, Object> getProfile() {
        return profile;
    }

    public void setProfile(Map<String, Object> profile) {
        this.profile = profile;
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

    public Map<String, Object> getCryptocurrency_addresses() {
        return cryptocurrency_addresses;
    }

    public void setCryptocurrency_addresses(Map<String, Object> cryptocurrency_addresses) {
        this.cryptocurrency_addresses = cryptocurrency_addresses;
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
}
