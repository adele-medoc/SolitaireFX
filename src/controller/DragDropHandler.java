package controller;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Carte;

public class DragDropHandler extends Controller {
//    ControllerTableau controller;

    public DragDropHandler(){}

//    public DragDropHandler(ControllerTableau controllerTableau) {
//        this.controller = controllerTableau;
//    }

    public void eventDrag(ImageView noeud){
        // Add mouse event handlers for the source
        noeud.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event)
            {
                noeud.setMouseTransparent(true);
                //System.out.println("Event on Source: mouse pressed");
                event.setDragDetect(true);
            }
        });

        noeud.setOnMouseReleased(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event)
            {
                noeud.setMouseTransparent(false);
                //System.out.println("Event on Source: mouse released");
            }
        });

        noeud.setOnMouseDragged(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event)
            {
                //System.out.println("Event on Source: mouse dragged");
                event.setDragDetect(false);
            }
        });

        noeud.setOnDragDetected(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event)
            {
                noeud.startFullDrag();
                //System.out.println("Event on Source: drag detected");
            }
        });

    }
    /*   méthode pour les le controle des évenemments drop, le target est situé sur un VBox
     * */
    public void eventDrop(VBox noeud, ControllerTableau controller){
        //System.out.println("************** passé par eventdragndrop VBox ***************");

        noeud.setOnMouseDragEntered(new EventHandler <MouseDragEvent>() {
            public void handle(MouseDragEvent event)
            {
                //System.out.println("Event on Target: mouse dragged");
            }
        });

        noeud.setOnMouseDragOver(new EventHandler <MouseDragEvent>() {
            public void handle(MouseDragEvent event)
            {
                //System.out.println("Event on Target: mouse drag over");
            }
        });

        noeud.setOnMouseDragReleased(new EventHandler <MouseDragEvent>() {
            public void handle(MouseDragEvent event)
            {
                //System.out.println("Event on Target: mouse drag released");
                //Appelle au controlleur pour savoir quoi faire lors du relachement de la souris
                EventType<MouseDragEvent> eventType = event.getEventType();
                ImageView ivSource = (ImageView) event.getGestureSource();
                VBox vBoxSource = (VBox) ivSource.getParent();
                Carte carteSource = (Carte) ivSource.getUserData();
                //Node i = event.getPickResult().getIntersectedNode();
                VBox vBoxTarget = noeud;
                Carte carteTarget = (Carte)vBoxTarget.getChildren().getLast().getUserData();;
                System.out.println("**************************** Mouvement Drag'n'Drop avant envoie au controlleur *************************************");
                System.out.println("source.getUserData() : " + carteSource.toString());
                System.out.println("event.getGestureSource() : " + (event.getGestureSource()));
                System.out.println("event.getGestureSource().getParents() : " + (((ImageView) event.getGestureSource()).getParent()));
                System.out.println("target = " + vBoxTarget.toString() +" carte target : "+ carteTarget);
                System.out.println("event.getPickResult().getIntersectedNode() = " + event.getPickResult().getIntersectedNode());
                System.out.println("*********************************************************************************************************************");
                controller.setCarteSource(carteSource);
                controller.setSource(vBoxSource);
                controller.setIvSource(ivSource);
                controller.setTarget(vBoxTarget);
                controller.setCarteTarget(carteTarget);
                controller.carteEstDeposable();
            }
        });

        noeud.setOnMouseDragExited(new EventHandler <MouseDragEvent>() {
            public void handle(MouseDragEvent event)
            {
                //System.out.println("Event on Target: mouse drag exited");
            }
        });

    }
}
