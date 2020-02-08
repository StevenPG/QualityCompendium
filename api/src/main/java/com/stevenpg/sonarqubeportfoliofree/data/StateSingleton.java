package com.stevenpg.sonarqubeportfoliofree.data;

import com.stevenpg.sonarqubeportfoliofree.loader.ProjectPage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
@Slf4j
public final class StateSingleton {

    private static StateSingleton instance;
    private String info = "Singleton that is updated every N seconds by loader";

    private ArrayList<ProjectPage> projectPages;
    private String sonarhosturl;
    private String basicAuthHeader;

    private StateSingleton(){}

    /**
     * Stream through all project pages for list of projects
     * @return all configured projects across pages
     */
    public String allProjectsAsCSV(){
        return projectPages.stream()
                .flatMap(projectPage -> projectPage.getProjectKeys().stream())
                .collect(Collectors.joining(","));
    }

    public String projectsAsCSV(String projectPage) {
        return projectPages.stream()
                .filter(page -> projectPage.equals(page.getPagename()))
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    public static StateSingleton getInstance() {
        if(instance == null){
            instance = new StateSingleton();
        }

        return instance;
    }
}
