module graphic_view {
    requires javafx.controls;
    requires java.desktop;
    exports graphic_view_controller; // controller pour vue console | graphic_view_controller; pour vue graphique
}