<?xml version="1.0" encoding="utf-8"?>
<CaR>
<Macro Name="p_bisector">
<Parameter name="P2">P2</Parameter>
<Parameter name="P1">P1</Parameter>
<Parameter name="P3">P3</Parameter>
<Comment>
<P>Winkelhalbierendenpaar des Winkels ABC mit Spitze B</P>
</Comment>
<Objects>
<Point name="P1" hidden="true" x="-4.104134762633997" y="-1.6539050535987752">Punkt in -4.104134762633997, -1.6539050535987752</Point>
<Point name="P2" hidden="true" x="-1.6784073506891277" y="1.1148545176110258">Punkt in -1.6784073506891277, 1.1148545176110258</Point>
<Point name="P3" hidden="true" x="0.012251148545175285" y="-1.7029096477794798">Punkt in 0.012251148545175285, -1.7029096477794798</Point>
<Line name="g1" hidden="true" from="P1" to="P3">Gerade durch P1 und P3</Line>
<Circle name="k1" hidden="true" through="P2" midpoint="P1" acute="true">Kreis um P1 durch P2</Circle>
<Intersection name="S1" hidden="true" first="g1" second="k1" shape="circle" which="first">Schnitt zwischen g1 und k1</Intersection>
<Intersection name="S2" hidden="true" first="g1" second="k1" shape="circle" which="second">Schnitt zwischen g1 und k1</Intersection>
<Midpoint name="M1" hidden="true" first="P2" second="S1" shape="circle">Mitte zwischen P2 und S1</Midpoint>
<Midpoint name="M2" hidden="true" first="P2" second="S2" shape="circle">Mitte zwischen P2 und S2</Midpoint>
<Line name="g2" target="true" from="P1" to="M2">Gerade durch P1 und M2</Line>
<Line name="g3" target="true" from="P1" to="M1">Gerade durch P1 und M1</Line>
</Objects>
</Macro>
<Macro Name="p3_circle">
<Parameter name="Punkt 2">Punkt 2</Parameter>
<Parameter name="Punkt 3">Punkt 3</Parameter>
<Parameter name="Punkt 4">Punkt 4</Parameter>
<Comment>
<P>Kreis durch drei vorgegebene Punkte</P>
</Comment>
<Objects>
<Point name="Punkt 2" hidden="true" mainparameter="true" x="-5.402756508422664" y="-0.5022970903522207">Punkt in -5.402756508422664, -0.5022970903522207</Point>
<Point name="Punkt 3" hidden="true" mainparameter="true" x="-1.7764165390505366" y="1.6049004594180705">Punkt in -1.7764165390505366, 1.6049004594180705</Point>
<Point name="Punkt 4" hidden="true" mainparameter="true" x="-0.33078101071975485" y="-1.1883614088820829">Punkt in -0.33078101071975485, -1.1883614088820829</Point>
<Segment name="Strecke 1" hidden="true" from="Punkt 2" to="Punkt 3">Strecke von Punkt 2 nach Punkt 3</Segment>
<Segment name="Strecke 2" hidden="true" from="Punkt 3" to="Punkt 4">Strecke von Punkt 3 nach Punkt 4</Segment>
<Midpoint name="Mitte 1" hidden="true" first="Punkt 3" second="Punkt 2" shape="diamond">Mitte zwischen Punkt 3 und Punkt 2</Midpoint>
<Midpoint name="Mitte 2" hidden="true" first="Punkt 3" second="Punkt 4" shape="diamond">Mitte zwischen Punkt 3 und Punkt 4</Midpoint>
<Plumb name="Lot 1" hidden="true" point="Mitte 1" line="Strecke 1" valid="true">Lot durch Mitte 1 zu Strecke 1</Plumb>
<Plumb name="Lot 2" hidden="true" point="Mitte 2" line="Strecke 2" valid="true">Lot durch Mitte 2 zu Strecke 2</Plumb>
<Intersection name="Schnitt 1" hidden="true" first="Lot 2" second="Lot 1" shape="diamond">Schnitt zwischen Lot 2 und Lot 1</Intersection>
<Circle name="Kreis 1" target="true" through="Punkt 4" midpoint="Schnitt 1" acute="true">Kreis um Schnitt 1 durch Punkt 4</Circle>
</Objects>
</Macro>
<Macro Name="pappusgerade">
<Parameter name="P1">P1</Parameter>
<Parameter name="P9">P9</Parameter>
<Parameter name="P2">P2</Parameter>
<Parameter name="P7">P7</Parameter>
<Parameter name="P10">P10</Parameter>
<Parameter name="P8">P8</Parameter>
<Comment>
<P>zu zwei mal drei je kollonearen Punkten wird die
Pappusgerade konstruiert.</P>
</Comment>
<Objects>
<Point name="P1" hidden="true" x="-4.667687595712097" y="0.5267993874425727">Punkt in -4.667687595712097, 0.5267993874425727</Point>
<Point name="P2" hidden="true" x="-1.9479326186830015" y="1.6049004594180705">Punkt in -1.9479326186830015, 1.6049004594180705</Point>
<Point name="P7" hidden="true" x="-4.128637059724349" y="-1.5558958652373664">Punkt in -4.128637059724349, -1.5558958652373664</Point>
<Point name="P8" hidden="true" x="-1.3598774885145481" y="-1.8009188361408885">Punkt in -1.3598774885145481, -1.8009188361408885</Point>
<Point name="P9" hidden="true" x="-3.41549767713042" y="1.023162958772247">Punkt in -3.41549767713042, 1.023162958772247</Point>
<Point name="P10" hidden="true" x="-2.729949828981097" y="-1.6796734962765922">Punkt in -2.729949828981097, -1.6796734962765922</Point>
<Line name="g1" type="thin" hidden="true" from="P1" to="P10">Gerade durch P1 und P10</Line>
<Line name="g2" type="thin" hidden="true" from="P10" to="P2">Gerade durch P10 und P2</Line>
<Line name="g3" type="thin" hidden="true" from="P7" to="P9">Gerade durch P7 und P9</Line>
<Line name="g4" type="thin" hidden="true" from="P9" to="P8">Gerade durch P9 und P8</Line>
<Intersection name="S1" type="thin" hidden="true" first="g3" second="g1" shape="circle">Schnitt zwischen g3 und g1</Intersection>
<Intersection name="S2" type="thin" hidden="true" first="g2" second="g4" shape="circle">Schnitt zwischen g2 und g4</Intersection>
<Line name="g5" target="true" from="S1" to="S2">Gerade durch S1 und S2</Line>
</Objects>
</Macro>
<Macro Name="tangente">
<Parameter name="k1">k1</Parameter>
<Parameter name="P2">P2</Parameter>
<Objects>
<Point name="P1" hidden="true" x="-0.6983154670750382" y="-1.1393568147013786">Punkt in -0.6983154670750382, -1.1393568147013786</Point>
<Point name="P2" hidden="true" x="-1.8989280245022968" y="0.7718223583460952">Punkt in -1.8989280245022968, 0.7718223583460952</Point>
<Circle name="k1" hidden="true" midpoint="P1">???</Circle>
<Segment name="s1" hidden="true" from="P1" to="P2">Strecke von P1 nach P2</Segment>
<Plumb name="l1" target="true" point="P2" line="s1" valid="true">Lot durch P2 zu s1</Plumb>
</Objects>
</Macro>
</CaR>
