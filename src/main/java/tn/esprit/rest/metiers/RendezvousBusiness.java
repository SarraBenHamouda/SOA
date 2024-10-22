package tn.esprit.rest.metiers;

import tn.esprit.rest.entities.Logement;
import tn.esprit.rest.entities.Rendezvous;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RendezvousBusiness {
    public static List<Rendezvous> listeRendezVous;
    LogementBusiness logementMetier=new LogementBusiness();
    public RendezvousBusiness() {
        listeRendezVous = new ArrayList<>();
    }


    public boolean addRendezVous(Rendezvous rendezVous){

        int refLogement=rendezVous.getLogement().getReference();

        Logement logement=logementMetier.getLogementsByReference(refLogement);

        if(logement!=null){
            rendezVous.setLogement(logement);
            return listeRendezVous.add(rendezVous);}
        return false;
    }
    public List<Rendezvous> getListeRendezVous() {
        return listeRendezVous;
    }

    public void setListeRendezVous(List<Rendezvous> listeRendezVous) {
        this.listeRendezVous = listeRendezVous;
    }
    public List<Rendezvous> getListeRendezVousByLogementReference(int reference) {
        List<Rendezvous> liste=new ArrayList<Rendezvous>();
        for(Rendezvous r:listeRendezVous){
            if(r.getLogement().getReference()==reference)
                liste.add(r);
        }
        return liste;
    }
    public Rendezvous getRendezVousById(int id){
        Rendezvous rendezVous=null;
        for(Rendezvous r:listeRendezVous){
            if(r.getId()==id)
                rendezVous=r;
        }
        return rendezVous;
    }
    public boolean deleteRendezVous(int id){
        Iterator<Rendezvous> iterator=listeRendezVous.iterator();
        while(iterator.hasNext()){
            Rendezvous r=iterator.next();
            if(r.getId()==id){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public boolean updateRendezVous(int idRendezVous, Rendezvous updatedRendezVous) {
        for (int i = 0; i < listeRendezVous.size(); i++) {
            Rendezvous r = listeRendezVous.get(i);
            if (r.getId() == idRendezVous) {

                Logement logement = logementMetier.getLogementsByReference(updatedRendezVous.getLogement().getReference());
                if (logement != null) {

                    listeRendezVous.set(i, updatedRendezVous);
                    return true;
                }
            }
        }
        return false;
    }

}