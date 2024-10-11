import model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsciiTest {
    AsciiPaint asc;
    // TEST : Constructeur Point
    @Test
    public void lePointACopierEstNull (){

        assertThrows(IllegalArgumentException.class, () -> new Point(null));

    }
    @Test
    public void abscisseNégative (){

        assertThrows(IllegalArgumentException.class, () -> new Point(-5, 10));

    }
    // teste Point meth move

    @Test
    public void lePointEstDéplacéSurLeBordGaucheDuDraw (){
        var p = new Point(10,10); // un objet Drawing  (avec valeur par defaut A été crée pour placer le point dessus
        p.move(-10,10) ;
        assertEquals(0,p.getX());
        assertEquals(20,p.getY());

    }
    @Test
    public void lePointEstDéplacéSurSupérieurDuDraw (){
        var p = new Point(10,10);
        p.move(10,-10) ;
        assertEquals(20,p.getX());
        assertEquals(0,p.getY());

    }
    @Test
    public void lePointEstDéplacéALOrigineDuDraw (){
        var p = new Point(10,10);
        p.move(-10,-10) ;
        assertEquals(0,p.getX());
        assertEquals(0,p.getY());

    }
    @Test
    public void lePointDéplacéLoinDesBordsDuDraw (){
        var p = new Point(10,10);
        p.move(10,10) ;
        assertEquals(20,p.getX());
        assertEquals(20,p.getY());

    }
    //TEST distanceTo
    @Test
    public void distanceTo() {
        var p1 = new Point(20,20);
        var p2 = new Point(0,0);
        assertEquals(Math.sqrt(800),p1.distanceTo(p2), 0.0);

    }
    @Test
    public void distanceTo_p1SupP2() {
        var p1 = new Point(20,20);
        var p2 = new Point(0,0);
        assertEquals(Math.sqrt(800),p1.distanceTo(p2), 0.0); // -20^2 + -20^2 = 800

    }
    @Test
    public void distanceTo_p2SupP1() {
        var p1 = new Point(10,10);
        var p2 = new Point(35,35);
        assertEquals(Math.sqrt(1250),p1.distanceTo(p2), 0.0); // 25^2 +25^2 = 625*2 = 1350

    }
    @Test
    public void distanceTo_PasDansLaShape() {

        asc = new AsciiPaint();
        asc.newRectangle(20,20,5,4,'R');
        var p = new Point(0,0);
        assertEquals(Math.sqrt(800),p.distanceTo(new Point(20,20)), 0.0);

    }
    //  TEST :Classe Circle:
    //  Constructeur
    @Test
    public void radiusNegatif (){
        assertThrows(IllegalArgumentException.class, () -> new Circle(new Point(7,7), -10,'R'));
    }
    @Test
    public void radiusNul (){
        assertThrows(IllegalArgumentException.class, () -> new Circle(new Point(7,7), 0,'R'));
    }
    @Test
    public void radiusStrictementPositif (){
       var c = new Circle(new Point(7,7), 10,'R');
        assertEquals(7,c.getCenter().getX());
        assertEquals(7,c.getCenter().getY());
        assertEquals(10,c.getRadius(),0.0);
        assertEquals('R',c.getColor(),0.0);

    }
    // Bounds
    @Test
    public void cir_Bounds (){
        var c = new Circle(new Point(5,5), 5,'T');
        var ul = new Point(0,0);
        var br = new Point(10,10);

        assertEquals(ul.getX(),c.Bounds().getUpperLeft().getX());
        assertEquals(br.getX(), c.Bounds().getBottomRight().getX());

        assertEquals(ul.getY(),c.Bounds().getUpperLeft().getY());
        assertEquals(br.getY(), c.Bounds().getBottomRight().getY());
    }

    //  move isInside
    @Test
    public void cir_isInside_isNot (){
        var c = new Circle(new Point(8,8), 5,'T');
        var p = new Point(0,0);
        assertFalse(c.isInside(p) );
    }
    @Test
    public void cir_isInside_isInside (){
        var c = new Circle(new Point(8,8), 5,'T');
        var p = new Point(10,10);
        assertTrue(c.isInside(p) );
    }
    @Test
    public void cir_isInside_TheCircleCenterPointisInside (){
        var c = new Circle(new Point(8,8), 5,'T');
        var p = new Point(8,8);

        assertTrue(c.isInside(p) );
    }
    @Test
    public void cir_isInside_PointAtBottomRight (){
        var c = new Circle(new Point(8,8), 5,'T');
        var p = new Point(11,12);
       assertTrue(c.isInside(p) );

    }

    // move pas de test car elle fait simplement appel à la methode move de la classe Point

    //  TEST :Classe Rectangle:
    //  Constructeurs
    @Test
    public void randomRectangle(){

        var r = new Rectangle(new Point(8,8), 10,15,'R');


        assertEquals(8,r.getUpperLeft().getX());
        assertEquals(8,r.getUpperLeft().getY());

        assertEquals(10,r.getHeight());
        assertEquals(15,r.getWidth());

        assertEquals('R',r.getColor(),0.0);
    }
    @Test
    public void rectHauteurNégative(){

        assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Point(8,8), -20,15,'R'));
    }
    @Test
    public void rectLargeurNégative(){

        assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Point(8,8), 20,-15,'R'));
    }
    @Test
    public void rectDimensionsNégatives(){

        assertThrows(IllegalArgumentException.class, () -> new Rectangle(new Point(8,8), -20,-15,'R'));
    }
    @Test
    public void rectBounds (){
        var r = new Rectangle(new Point(35,35), 5,4,'R');
        var ul = new Point(35,35);
        var br = new Point(39,40);

        assertEquals(ul.getX(),r.Bounds().getUpperLeft().getX());
        assertEquals(br.getX(), r.Bounds().getBottomRight().getX());

        assertEquals(ul.getY(),r.Bounds().getUpperLeft().getY());
        assertEquals(br.getY(), r.Bounds().getBottomRight().getY());
    }

    //  isInside
    @Test
    public void rect_isInside_isNot (){

        var r = new Rectangle(new Point(35,35), 5,4,'R');
        var p = new Point(10,10);
        assertFalse(r.isInside(p) );
    }
    @Test
    public void rec_isInside_isIn (){
        var r = new Rectangle(new Point(35,35), 5,4,'R');
        var p = new Point(38,38);
        assertTrue(r.isInside(p) );
    }
    @Test
    public void isInside_TheUpperLeftPointisInside (){
        var r = new Rectangle(new Point(35,35), 5,4,'R');
        var p = new Point(35,35);

        assertTrue(r.isInside(p) );
    }    @Test
    public void isInside_TheBottomRightPointisInside (){
        var r = new Rectangle(new Point(35,35), 5,4,'R');
        var p = new Point(39,40);

        assertTrue(r.isInside(p) );
    }

    // move pas de test car elle fait simplement appel à la methode move de la classe Point

    // TEST : Classe Square

    @Test
    public void squareCotéNégatif(){

        assertThrows(IllegalArgumentException.class, () -> new Square(new Point(5,5), -20,'R'));
    }
    @Test
    public void squareRandom(){
        var s = new Square (new Point(8,8), 20,'R');

        assertEquals(8,s.getUpperLeft().getX());
        assertEquals(8,s.getUpperLeft().getY());
        assertEquals(20,s.getHeight());
        assertEquals('R',s.getColor(),0.0);
    }

    // TEST Classe Drawing
    // Constructeur

    @Test
  public void hauteurNégative (){

        var c = new Circle(new Point(7,7), 10,'R');
        assertThrows(IllegalArgumentException.class, () -> new AsciiPaint(-5, 10));

    }
    @Test
   public void largeurNégative (){

        assertThrows(IllegalArgumentException.class, () -> new AsciiPaint(18,-6));

    }


    @Test
   public void hauteurNulle (){
        assertThrows(IllegalArgumentException.class, () -> new AsciiPaint(0, 10));

    }
     @Test
   public void largeurNulle (){

         assertThrows(IllegalArgumentException.class, () -> new AsciiPaint(15, 0));

     }
     @Test
   public void HauteurEtLargeurNulles (){

         assertThrows(IllegalArgumentException.class, () -> new AsciiPaint(0, 0));

     }
    @Test
    public void hauteuretLargeursStrictementPositives (){

        asc = new AsciiPaint(10, 12);

    }
     @Test
    public void addASquare (){

        var drawing = new Drawing();
        var s = new Square(new Point(22,21),10,'S');
        drawing.addShape(s);
        assertEquals( s, drawing.getShapeAt(s.getUpperLeft()));

     }
    @Test
    public void addNoShape (){

        var drawing = new Drawing();
        assertNull(drawing.getShapeAt(new Point(10,15)));
    }
    @Test
    public void addACircle (){

        var drawing = new Drawing();
        var c = new Circle(new Point(17,17),10,'C');
        drawing.addShape(c);
        assertEquals( c, drawing.getShapeAt(c.getCenter()));
    }
    @Test
    public void addARectangle (){

        var drawing = new Drawing();
        var r = new Rectangle(new Point(22,21),10,5,'R');
        drawing.addShape(r);
        assertEquals( r, drawing.getShapeAt(r.getUpperLeft()));

    }
    // getShapeAt()
    @Test
    public void getShapeAt_getARectangle (){

        var drawing = new Drawing();
        var r = new Rectangle(new Point(38,36),10,6,'R');
        drawing.addShape(r);
        assertEquals(r, drawing.getShapeAt(new Point(40,42)));
    }
    @Test
    public void getShapeAt_getASquare (){

      var drawing = new Drawing();
      var s = new Square(new Point(10,10),10,'S');
      drawing.addShape(s);
      assertEquals(s, drawing.getShapeAt(new Point(15,15)));
    }
    @Test
    public void getShapeAt_NoShapeForThisPoint (){

      var drawing = new Drawing();
    assertNull(drawing.getShapeAt(new Point(15,15)));
    }
    @Test
    public void getShapeAt_getACircle (){
        var drawing = new Drawing();
        var c = new Circle(new Point(25,25),10,'C');
        drawing.addShape(c);
        assertEquals(c, drawing.getShapeAt(new Point(20,20)));
    }    @Test
    public void setAShapeThatIsNotInTheList (){

        var drawing = new Drawing();

        assertThrows(IllegalArgumentException.class, () -> drawing.setShapeC(2,'c'));
    }

    @Test
    public void setFirstAddedShapeColor (){

        var drawing = new Drawing();
        var c = new Circle(new Point(25,25),10,'C');
        drawing.addShape(c);
        drawing.setShapeC(0,'c');
        assertEquals('c', c.getColor());
    }
    @Test
    public void setLastAddedShapeColor (){

        var drawing = new Drawing();
        var c = new Circle(new Point(25,25),10,'C');
        var s = new Square(new Point(3,10),4,'S');
        var r = new Rectangle(new Point(38,36),10,6,'R');


        drawing.addShape(c);
        drawing.addShape(s);
        drawing.addShape(r);

        drawing.setShapeC(2,'r');
        assertEquals('r', r.getColor());
    }
    @Test
    public void setMiddleAddedShapeColor (){

        var drawing = new Drawing();
        var c = new Circle(new Point(25,25),10,'C');
        var s = new Square(new Point(3,10),4,'S');
        var r = new Rectangle(new Point(38,36),10,6,'R');

        drawing.addShape(c);
        drawing.addShape(s);
        drawing.addShape(r);

        drawing.setShapeC(1,'s');
        assertEquals('s', s.getColor());
    }
    @Test
    public void moveShape_formeDIndiceIPasEncoreAjoutée (){

        var drawing = new Drawing();
        assertThrows(IllegalArgumentException.class, () ->  drawing.moveShape(0,5,5));
    }

     //  moveShape
     @Test
     public void moveMiddleAddedShape (){

         var drawing = new Drawing();

         var s = new Square(new Point(3,10),4,'S');
         var c = new Circle(new Point(25,25),10,'C');
         var r = new Rectangle(new Point(38,36),10,6,'R');

         drawing.addShape(s);
         drawing.addShape(c);
         drawing.addShape(r);

         drawing.moveShape(1,5,5);
         assertEquals(30, c.getCenter().getX());
         assertEquals(30, c.getCenter().getY());

     }
     @Test
     public void moveLastAddedShape (){

         var drawing = new Drawing();

         var s = new Square(new Point(3,10),4,'S');
         var r = new Rectangle(new Point(38,36),10,6,'R');

         drawing.addShape(s);
         drawing.addShape(r);

         drawing.moveShape(1,6,6);
         assertEquals(44, r.getUpperLeft().getX());
         assertEquals(42, r.getUpperLeft().getY());
     }
     @Test
     public void moveFirstAddedShape (){

         var drawing = new Drawing();

         var s = new Square(new Point(3,10),4,'S');

         drawing.addShape(s);

         drawing.moveShape(0,6,6);
         assertEquals(9, s.getUpperLeft().getX());
         assertEquals(16, s.getUpperLeft().getY());
     }
}
