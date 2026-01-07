module fr.univartois.butinfo.r304.pacman {

    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires transitive fr.univartois.dpprocessor;

    exports fr.univartois.butinfo.r304.pacman;
    exports fr.univartois.butinfo.r304.pacman.controller;
    exports fr.univartois.butinfo.r304.pacman.view;

    opens fr.univartois.butinfo.r304.pacman to javafx.graphics;
    opens fr.univartois.butinfo.r304.pacman.controller to javafx.fxml;
}
