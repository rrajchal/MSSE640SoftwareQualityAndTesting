package com.triangle.springproject;

import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

@RestController
public class TriangleController {
    TriangleDb db = new TriangleDb();

    @GetMapping("/triangle")
    public String classifyTriangle(@RequestParam double sideA, @RequestParam double sideB, @RequestParam double sideC) {
        Triangle triangle = new Triangle(sideA, sideB, sideC);
        return triangle.getTriangleType().toString();
    }

    @PostMapping(value = "/triangle", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String classifyTriangle(@RequestBody String requestBody) {
        Triangle triangle;
        Gson gson = new Gson();
        try {
            triangle = gson.fromJson(requestBody, Triangle.class);
            //if (db.getTriangle(triangle);
            System.out.println(triangle);
            return triangle.getTriangleType().toString();
        } catch (Exception e) {
            return TriangleType.INVALID.toString() + " Data";
        }
    }

    @PostMapping(value = "/triangle/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveTriangle(@RequestBody String requestBody) {
        db = new TriangleDb();
        Triangle triangle;
        Gson gson = new Gson();
        try {
            triangle = gson.fromJson(requestBody, Triangle.class);
            if (triangle.getTriangleType().equals(TriangleType.INVALID))
                return triangle.getTriangleType().toString() + " cannot be saved in the database.";
            JSONArray json = db.getTriangle(triangle);
            if (json == null || json.isEmpty()) {
                db.saveTriangle(triangle);
                return triangle.getTriangleType().toString() + " saved";
            } else {
                return triangle.getTriangleType().toString() + " already in the database.";
            }
        } catch (Exception e) {
            return TriangleType.INVALID.toString() + " Data";
        } finally {
            db.close();
        }
    }

    @GetMapping(value = "/triangle/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTriangle() {
        db = new TriangleDb();
        try {
            JSONArray json = db.getTriangle(null);
            if (json == null)
                return "No Record found.";
            return json.toString();
        } catch (Exception e) {
            return TriangleType.INVALID.toString() + " Data";
        } finally {
            db.close();
        }
    }

    @GetMapping(value = "/triangle/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTriangle(@RequestBody String requestBody) {
        db = new TriangleDb();
        Triangle triangle = null;
        Gson gson = new Gson();
        try {
            triangle = gson.fromJson(requestBody, Triangle.class);
            JSONArray json = db.getTriangle(triangle);
            System.out.println("Json: " + json);
            return json != null ? json.toString() : "No Record";
        } catch (Exception e) {
            return TriangleType.INVALID.toString() + " Data";
        } finally {
            db.close();
        }
    }

    @DeleteMapping(value = "/triangle/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteTriangle(@RequestBody String requestBody) {
        db = new TriangleDb();
        Triangle triangle;
        Gson gson = new Gson();
        try {
            triangle = gson.fromJson(requestBody, Triangle.class);
            if (db.getTriangle(triangle) != null) {
                db.deleteTriangle(triangle);
                return triangle.getTriangleType().toString() + " deleted from the database";
            } else {
                return triangle.getTriangleType().toString() + " not found in the database.";
            }
        } catch (Exception e) {
            return TriangleType.INVALID.toString() + " Data";
        } finally {
            db.close();
        }
    }
}