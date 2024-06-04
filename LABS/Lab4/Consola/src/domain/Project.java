package domain;

import java.util.LinkedList;

import javax.swing.JOptionPane;

//import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project
 * 
 * @author POOB
 * @version ECI 2022
 */

public class Project {
    private HashMap<String, Activity> activities;

    /**
     * Create a Project
     */
    public Project() {
        activities = new HashMap<String, Activity>();
        addSome();
    }

    private void addSome() {
        String[][] activities = { { "Buscar datos", "50", "50", "" },
                { "Evaluar datos", "80", "80", "" },
                { "Limpiar datos", "100", "100", "" },
                { "Preparar datos", "50", "Secuencial",
                        "Buscar datos\nEvaluar datos\nLimpiar datos" } };
        for (String[] c : activities) {
            try {
                add(c[0], c[1], c[2], c[3]);
            } catch (ProjectException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Consult a activity
     * 
     * @param name the name of the activity
     * @return
     */
    public Activity consult(String name) {
        return activities.get(name.toUpperCase());
    }

    /**
     * Add a new activity
     * @param name the name of the activity
     * @param cost the cost of the activity
     * @param timeType the time of the activity
     * @param theActivities the sub-activities of the activity
     * @throws ProjectException
     */
    public void add(String name, String cost, String timeType, String theActivities) throws ProjectException{ 
        Activity na;
        if((!esEntero(cost)&&!cost.equals("")) || (!esEntero(timeType)&&!timeType.equals("") &&!(timeType.toUpperCase().equals("PARALELA") || timeType.toUpperCase().equals("SECUENCIAL")))){
            throw new ProjectException(ProjectException.WRONG_TYPE);
        } else {
            if (theActivities.equals("")){
                
                na=new Simple(name,cost.equals("") ? null : Integer.parseInt(cost),timeType.equals("") ? null : Integer.parseInt(timeType));
            }else{ 
                na = new Composed(name,cost.equals("") ? null : Integer.parseInt(cost), timeType.equals("") ? true : timeType.toUpperCase().charAt(0)=='P');
                String [] aSimples= theActivities.split("\n");
                for (String b : aSimples){
                    if(activities.containsKey(b.toUpperCase())){
                        ((Composed)na).add(activities.get(b.toUpperCase()));
                    }else {
                        throw new ProjectException(ProjectException.UNKNOWN);
                    }
                }
            }
        }
        if(activities.containsKey(name.toUpperCase())){
            throw new ProjectException(ProjectException.EXISTENT_ACTIVITY);
        } else {
            activities.put(name.toUpperCase(),na);
        }
    }

    

    /**
     * Consults the activities that start with a prefix
     * 
     * @param prefix the prefix of the activity to search
     * @return
     */

    public LinkedList<Activity> select(String prefix) {
        LinkedList<Activity> answers = new LinkedList<>();
        prefix = prefix.toUpperCase();
        try {
            for (Activity activity : this.activities.values()) {
                if (activity.name().toUpperCase().startsWith(prefix.toUpperCase())) {
                    answers.add(activity);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha presentado un error en la busqueda");
            Log.record(e);
        }

        return answers;
    }

    /**
     * Consult selected activities
     * 
     * @param selected the activities to consult
     * @return
     */
    public String data(LinkedList<Activity> selected) {
        StringBuffer answer = new StringBuffer();
        answer.append(activities.size() + " actividades\n");
        for (Activity p : selected) {
            try {
                answer.append('>' + p.data());
                answer.append("\n");
            } catch (ProjectException e) {
                answer.append("**** " + e.getMessage());
            }
        }
        return answer.toString();
    }

    /**
     * Return the data of activities with a prefix
     * 
     * @param prefix the prefix of the activity to search
     * @return
     */
    public String search(String prefix) {
        return data(select(prefix));
    }

    /**
     * Return the data of all activities
     * 
     * @return
     */
    public String toString() {
        return data(new LinkedList<>(activities.values()));
    }

    /**
     * Consult the number of activities
     * 
     * @return
     */
    public int numberActivitys() {
        return activities.size();
    }

    public static boolean esEntero(String str) {
        try {
            Integer.parseInt(str); // Intenta convertir la cadena a un entero
            return true; // Si no hay excepción, la cadena es un entero
        } catch (NumberFormatException e) {
            return false; // Si hay una excepción, la cadena no es un entero
        }
    }
}
