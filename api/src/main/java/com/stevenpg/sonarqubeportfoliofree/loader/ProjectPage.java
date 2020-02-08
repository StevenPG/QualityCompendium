package com.stevenpg.sonarqubeportfoliofree.loader;

import lombok.Data;

import java.util.ArrayList;

/**
 * Example JSON:
 * {
 *       "PageName" : "PageOne",
 *       "projectKeys" : [
 *         "project1",
 *         "project2"
 *       ]
 *     }
 */
@Data
public class ProjectPage {
    String pagename;
    ArrayList<String> projectKeys;
}
