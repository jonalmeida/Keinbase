package com.jonalmeida.keinbase.pojos;

import java.util.List;

public class Components {
    private Component username;
    private FingerprintComponent key_fingerprint;
    private Component full_name;
    private Component twitter;
    private Component github;
    private Component reddit;
    private Component hackernews;
    private Component coinbase;
    private List<WebsiteComponent> websites;

    public Component getUsername() {
        return username;
    }

    public void setUsername(Component username) {
        this.username = username;
    }

    public FingerprintComponent getKey_fingerprint() {
        return key_fingerprint;
    }

    public void setKey_fingerprint(FingerprintComponent key_fingerprint) {
        this.key_fingerprint = key_fingerprint;
    }

    public Component getFull_name() {
        return full_name;
    }

    public void setFull_name(Component full_name) {
        this.full_name = full_name;
    }

    public Component getTwitter() {
        return twitter;
    }

    public void setTwitter(Component twitter) {
        this.twitter = twitter;
    }

    public Component getGithub() {
        return github;
    }

    public void setGithub(Component github) {
        this.github = github;
    }

    public Component getReddit() {
        return reddit;
    }

    public void setReddit(Component reddit) {
        this.reddit = reddit;
    }

    public Component getHackernews() {
        return hackernews;
    }

    public void setHackernews(Component hackernews) {
        this.hackernews = hackernews;
    }

    public Component getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(Component coinbase) {
        this.coinbase = coinbase;
    }

    public List<WebsiteComponent> getWebsites() {
        return websites;
    }

    public void setWebsites(List<WebsiteComponent> websites) {
        this.websites = websites;
    }
}
