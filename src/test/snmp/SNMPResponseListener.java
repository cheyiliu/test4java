/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.snmp;

import java.util.Vector;
import org.snmp4j.smi.VariableBinding;

/**
 * @author brnunes
 */
public interface SNMPResponseListener {
    public void onSNMPResponseReceived(Vector<? extends VariableBinding> variableBinding);
}
