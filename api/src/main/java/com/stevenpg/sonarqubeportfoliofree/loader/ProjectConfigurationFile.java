package com.stevenpg.sonarqubeportfoliofree.loader;

import lombok.Data;

import java.util.ArrayList;
import java.util.Base64;

/**
 * Example File:
 * {
 *   "sonarhosturl": "http://192.168.2.155:19100",
 *   "projectPages" : [
 *     ...
 *   ],
 *   "sonarReadonlyUser" : "admin",
 *   "sonarReadonlyPass" : "admin"
 * }
 */
@Data
public class ProjectConfigurationFile {
    private String sonarhosturl;
    private ArrayList<ProjectPage> projectPages;
    private String sonarReadonlyUser;
    private String sonarReadonlyPass;

    public String base64Credentials() {
        return Base64.getEncoder().encodeToString((sonarReadonlyUser + ":" + sonarReadonlyPass).getBytes());
    }
}
