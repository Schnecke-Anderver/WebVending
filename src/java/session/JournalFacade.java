/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Journal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dilerom
 */
@Stateless
public class JournalFacade extends AbstractFacade<Journal> {

    @PersistenceContext(unitName = "WebVendingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JournalFacade() {
        super(Journal.class);
    }
    
   
}
