package com.triangle.springproject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

@RestController
public class TriangleController {
    TriangleDb db = new TriangleDb();

    @GetMapping("/triangle")
    public String classifyTriangle(@RequestParam double sideA, @RequestParam double sideB, @RequestParam double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        db.saveTriangle(triangle);
        db.close();
        return triangle.getTriangleType().toString();
    }

    @PostMapping(value = "/triangle", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String classifyTriangle(@RequestBody String requestBody) {
        Triangle triangle;
        Gson gson = new Gson();
        try {
            triangle = gson.fromJson(requestBody, Triangle.class);
            db.saveTriangle(triangle);
            db.close();
            System.out.println(triangle);
            return triangle.getTriangleType().toString();
        } catch (Exception e) {
            return TriangleType.INVALID.toString() + " Data";
        }
    }
}