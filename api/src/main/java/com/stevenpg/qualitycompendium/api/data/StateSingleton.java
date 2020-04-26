package com.stevenpg.qualitycompendium.api.data;

import com.stevenpg.qualitycompendium.api.datastore.ProjectPageRepository;
import com.stevenpg.qualitycompendium.api.loader.ProjectPage;
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

    public static StateSingleton getInstance() {
        if(instance == null){
            instance = new StateSingleton();
        }

        return instance;
    }
}
