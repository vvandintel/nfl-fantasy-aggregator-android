package com.vincentvandintel.fantasyaggregator.viewModel;

import android.text.Html;
import android.text.Spanned;

import com.vincentvandintel.fantasyaggregator.model.PlayerNewsItem;

/**
 * Created by vvand on 9/28/2017.
 */

public class PlayerNewsCard {
    private PlayerNewsItem player;
    private int cardPosition;
    private String name;
    private Spanned info;
    private Spanned content;

    public PlayerNewsCard(PlayerNewsItem player) {
        this.player = player;

        format();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Spanned getInfo() {
        return info;
    }

    private void setInfo(Spanned info) {
        this.info = info;
    }

    public Spanned getContent() {
        return content;
    }

    private void setContent(Spanned content) {
        this.content = content;
    }

    private void format() {
        setName(player.getFirstName()
                .concat(" ")
                .concat(player.getLastName()));

        String htmlEncodedInfo = player.getPosition()
                .concat(", ")
                .concat(player.getTeamAbbr());

        String htmlEncodedContent = player.getBody()
                .concat(player.getAnalysis());

        setInfo(getHtmlDecodedText(htmlEncodedInfo));
        setContent(getHtmlDecodedText(htmlEncodedContent));
    }

    private Spanned getHtmlDecodedText (String htmlEncodedText) {
        return Html.fromHtml(htmlEncodedText, Html.FROM_HTML_MODE_LEGACY);
    }

}
