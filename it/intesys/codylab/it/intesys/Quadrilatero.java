package it.intesys.codylab.it.intesys;

public abstract class Quadrilatero implements FormaGeometrica{

  private final float lato1 = 2;
  private final float lato2 = 3;
  private final float lato3 = 4;
  private final float lato4 = 6;

    @Override
    public float perimetro() {
        return this.lato1 + this.lato2 + this.lato3 + this.lato4;
    }
}
