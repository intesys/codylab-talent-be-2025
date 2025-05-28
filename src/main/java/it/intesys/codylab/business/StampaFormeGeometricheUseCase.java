package it.intesys.codylab.business;

import it.intesys.codylab.model.FormaGeometrica;
import it.intesys.codylab.repository.FormaGeometricaRepository;

public class StampaFormeGeometricheUseCase {

    private final FormaGeometricaRepository formaGeometricaRepository;

    public StampaFormeGeometricheUseCase(FormaGeometricaRepository formaGeometricaRepository) {
        this.formaGeometricaRepository = formaGeometricaRepository;
    }

    public void stampaFormeGeometriche() {
        formaGeometricaRepository.findAll().forEach(this::stampaPerimetroArea);
    }

    private void stampaPerimetroArea(FormaGeometrica formaGeometrica) {
        System.out.println("Forma geometrica è " + formaGeometrica.toString());
        System.out.println("perimetro: " + formaGeometrica.perimetro());
        System.out.println("area: " + formaGeometrica.area());
    }

}
