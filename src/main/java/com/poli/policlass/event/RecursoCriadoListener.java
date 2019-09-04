package com.poli.policlass.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;


@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {
    @Override
    public void onApplicationEvent(RecursoCriadoEvent event) {
        HttpServletResponse response = event.getResponse();
        Long id = event.getId();

        adicionaHeaderLocation(response, id);
    }

    private void adicionaHeaderLocation(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }


}
