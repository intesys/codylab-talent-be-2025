package it.intesys.codylab;

import it.intesys.codylab.business.StampaFormeGeometricheUseCase;
import it.intesys.codylab.repository.FormaGeometricaRepository;

public class MyCodyLabApplication {

    public static void main(String[] args) {
        StampaFormeGeometricheUseCase stampaFormeGeometricheUseCase = new MyCodyLabApplication().initStampaFormeGeometricheUseCase();
        stampaFormeGeometricheUseCase.stampaFormeGeometriche();
    }

    private StampaFormeGeometricheUseCase initStampaFormeGeometricheUseCase() {
        return new StampaFormeGeometricheUseCase(new FormaGeometricaRepository());
    }

}
