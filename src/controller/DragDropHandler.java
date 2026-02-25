package controller;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import models.Carte;

public class DragDropHandler extends Controller {
//    ControllerTableau controller;

    public DragDropHandler(){}

//    public DragDropHandler(ControllerTableau controllerTableau) {
//        this.controller = controllerTableau;
//    }

    public void eventDrag(ImageView noeud, ControllerTableau controller){
        noeud.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                // début du dragNdrop
                Dragboard db = noeud.startDragAndDrop(TransferMode.ANY);

                /* dragboard = Représente le presse-papiers du système d'exploitation */
                ClipboardContent content = new ClipboardContent();
                content.putString(noeud.getUserData().toString());
                content.putImage(noeud.getImage());
//                System.out.println("ClipboardContent = "+content);
                db.setContent(content);
                VBox vbSource = (VBox) noeud.getParent();

                controller.setSource(vbSource);
                controller.setIvSource(noeud);
                controller.setCarteSource((Carte) noeud.getUserData());
                controller.setIndexCarteColonne(vbSource.getChildren().indexOf(noeud));
//                System.out.println("carte source : "+noeud.getUserData().toString());
//                System.out.println("vbSource.getChildren().indexOf(noeud) -> index de la carte dans la colonne "+ vbSource.getChildren().indexOf(noeud) + " sur " + vbSource.getChildren().indexOf(vbSource.getChildren().getLast()));
                event.consume();
            }
        });

        noeud.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                //Si les données ont été déplacées avec succès, on les efface
                if (event.getTransferMode() == TransferMode.MOVE) {
                    //source.setText("");
                }
                event.consume();
            }
        });
//        // Add mouse event handlers for the source
//        noeud.setOnMousePressed(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent event)
//            {
//                noeud.setMouseTransparent(true);
//                //System.out.println("Event on Source: mouse pressed");
//                event.setDragDetect(true);
//            }
//        });
//
//        noeud.setOnMouseReleased(new EventHandler <MouseEvent>() {
//            public void handle(MouseEvent event)
//            {
//                noeud.setMouseTransparent(false);
//                //System.out.println("Event on Source: mouse released");
//            }
//        });
//
//        noeud.setOnMouseDragged(new EventHandler <MouseEvent>() {
//            public void handle(MouseEvent event)
//            {
//                //System.out.println("Event on Source: mouse dragged");
//                event.setDragDetect(false);
//            }
//        });
//
//        noeud.setOnDragDetected(new EventHandler <MouseEvent>() {
//            public void handle(MouseEvent event)
//            {
//                noeud.startFullDrag();
//                //System.out.println("Event on Source: drag detected");
//            }
//        });



    }
    /*   méthode pour les le controle des évenemments drop, le target est situé sur un VBox
     * */
    public void eventDrop(VBox noeud, ControllerTableau controller){
        //System.out.println("************** passé par eventdragndrop VBox ***************");

        noeud.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // noeud survolé par la souris qui peut potentiellement recevoir les données
                if (event.getGestureSource() != noeud &&
                        //c'est le dragboard qui détermine si on peut poser ou non une carte à un endroit
                        event.getDragboard().hasString()) {
                    System.out.println();

                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

/*        //montre à l'utilisateur si le noeud peut être déplacé ici
        noeud.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                //montre à l'utilisateur si le noeud peut être déplacé ici
                if (event.getGestureSource() != noeud &&
                        event.getDragboard().hasString()) {
                    //target.setFill(Color.GREEN);
                }

                event.consume();
            }
        });
        // retour à l'état initial pour après avoir montré à l'utilisateur si le noeud peut être déplacé ici
        noeud.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                //target.setFill(Color.BLACK);

                event.consume();
            }
        });
*/

        noeud.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* donnée supprimé,  */
                /* si il y a des data dans le dragboard, les utilise */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    VBox vBoxTarget = noeud;
                Carte carteTarget = (Carte)vBoxTarget.getChildren().getLast().getUserData();;
//                System.out.println("**************************** Mouvement Drag'n'Drop avant envoie au controlleur *************************************");
//                //System.out.println("source.getUserData() : " + carteSource.toString());
//                System.out.println("event.getGestureSource() : " + (event.getGestureSource()));
//                System.out.println("event.getGestureSource().getParents() : " + (((ImageView) event.getGestureSource()).getParent()));
//                System.out.println("target = " + vBoxTarget.toString() +" carte target : "+ carteTarget);
//                System.out.println("*********************************************************************************************************************");
                //controller.setCarteSource(((Carte) event.getGestureSource()).getUserData());
                //controller.setSource((VBox) ((ImageView) event.getGestureSource()).getParent());
                //controller.setIvSource((ImageView) event.getGestureSource());
                controller.setTarget(vBoxTarget);
                controller.setCarteTarget(carteTarget);
                controller.carteEstDeposable();
                    success = true;
                }
                //Indiquer à la source si la chaîne a été transmise avec succès
                event.setDropCompleted(success);

                event.consume();
            }
        });

//
//        noeud.setOnMouseDragEntered(new EventHandler <MouseDragEvent>() {
//            public void handle(MouseDragEvent event)
//            {
//                //System.out.println("Event on Target: mouse dragged");
//            }
//        });
//
//        noeud.setOnMouseDragOver(new EventHandler <MouseDragEvent>() {
//            public void handle(MouseDragEvent event)
//            {
//                //System.out.println("Event on Target: mouse drag over");
//            }
//        });
//
//        noeud.setOnMouseDragReleased(new EventHandler <MouseDragEvent>() {
//            public void handle(MouseDragEvent event)
//            {
//                //System.out.println("Event on Target: mouse drag released");
//                //Appelle au controlleur pour savoir quoi faire lors du relachement de la souris
//                EventType<MouseDragEvent> eventType = event.getEventType();
//                ImageView ivSource = (ImageView) event.getGestureSource();
//                VBox vBoxSource = (VBox) ivSource.getParent();
//                Carte carteSource = (Carte) ivSource.getUserData();
//                //Node i = event.getPickResult().getIntersectedNode();
//                VBox vBoxTarget = noeud;
//                Carte carteTarget = (Carte)vBoxTarget.getChildren().getLast().getUserData();;
//                System.out.println("**************************** Mouvement Drag'n'Drop avant envoie au controlleur *************************************");
//                System.out.println("source.getUserData() : " + carteSource.toString());
//                System.out.println("event.getGestureSource() : " + (event.getGestureSource()));
//                System.out.println("event.getGestureSource().getParents() : " + (((ImageView) event.getGestureSource()).getParent()));
//                System.out.println("target = " + vBoxTarget.toString() +" carte target : "+ carteTarget);
//                System.out.println("event.getPickResult().getIntersectedNode() = " + event.getPickResult().getIntersectedNode());
//                System.out.println("*********************************************************************************************************************");
//                controller.setCarteSource(carteSource);
//                controller.setSource(vBoxSource);
//                controller.setIvSource(ivSource);
//                controller.setTarget(vBoxTarget);
//                controller.setCarteTarget(carteTarget);
//                controller.carteEstDeposable();
//            }
//        });
//
//        noeud.setOnMouseDragExited(new EventHandler <MouseDragEvent>() {
//            public void handle(MouseDragEvent event)
//            {
//                //System.out.println("Event on Target: mouse drag exited");
//            }
//        });

    }
}
