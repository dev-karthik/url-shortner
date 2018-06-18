package com.mylabs.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.hash.Hashing;
import com.mylabs.model.URLStore;
import com.mylabs.service.IUrlStoreService;

@Controller
public class UrlController {
    @Autowired
    private IUrlStoreService urlStoreService;

    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public String showForm() {
        return "shortener";
    }

    @RequestMapping(value = "/url/{id}", method = RequestMethod.GET)
    public void redirectToUrl(@PathVariable String id, HttpServletResponse resp) throws Exception {
        final String url = urlStoreService.findUrlById(id);
        if (url != null) {
            resp.addHeader("Location", url);
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value="/url", method = RequestMethod.POST)
    public ModelAndView shortenUrl(HttpServletRequest httpRequest,
                                   @Valid URLStore request,
                                   BindingResult bindingResult) {
        String url = request.getLongUrl();
        if (!isUrlValid(url)) {
            bindingResult.addError(new ObjectError("url", "Invalid url format: " + url));
        }

        ModelAndView modelAndView = new ModelAndView("shortener");
        if (!bindingResult.hasErrors()) {
            final String id = Hashing.murmur3_32()
                .hashString(url, StandardCharsets.UTF_8).toString();
            request.setShortUrl(id);
            urlStoreService.storeUrl(request);
            String requestUrl = httpRequest.getRequestURL().toString();
            String prefix = requestUrl.substring(0, requestUrl.indexOf(httpRequest.getRequestURI(),
                "http://".length()));

            modelAndView.addObject("shortenedUrl", prefix + "/" + id);
        }
        return modelAndView;
    }

    private boolean isUrlValid(String url) {
        boolean valid = true;
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            valid = false;
        }
        return valid;
    }
}
