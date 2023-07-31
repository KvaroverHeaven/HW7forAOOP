/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prismdown.hw7;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @author kvarnoel
 */

public class ODMDView extends JFrame {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -1780260391092946400L;
    private static final JButton dJson = new JButton("下載");
    private static final JTextArea response = new JTextArea(50, 50);
    private static final String requestUri = "https://support.oneskyapp.com/hc/en-us/article_attachments/202761727/example_2.json";

    public ODMDView() {
        super("HW7");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 20, 1, 20);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;

        response.setEditable(false);
        response.setVisible(true);
        response.setLineWrap(true);
        response.setWrapStyleWord(true);

        add(dJson, c);

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 260;
        c.weightx = 1;
        c.insets = new Insets(0, 20, 20, 20);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        add(response, c);

        dJson.addActionListener(e -> {
            if (!dJson.isEnabled()) {
                return;
            }

            dJson.setEnabled(false);
            ExecutorService executor = Executors.newCachedThreadPool();
            Callable<?> task = ODMDView::processJson;
            FutureTask<?> future = new FutureTask<>(task);
            executor.execute(future);
        });

    }

    private static Object processJson() {
        try (InputStream stream = new URI(requestUri).toURL().openStream();
                JsonReader reader = Json.createReader(stream)) {
            Optional<JsonObject> jsonObject = Optional.of(reader.readObject());
            System.out.println(jsonObject.get().toString());
            if (jsonObject.isPresent()) {
                response.append("Successfully receive json\n");
                if (ODMDModel.insertJson(jsonObject.get().toString())) {
                    response.append("Successfully parse json into document\n");
                } else {
                    response.append("Unsuccessfully parse json into document\n");
                }
            } else {
                response.append("Unsuccessfully receive json\n");
            }
            dJson.setEnabled(true);

        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
