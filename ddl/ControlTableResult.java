package ddl;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class ControlTableResult  implements ListSelectionListener{
	CreateTableGUI createFrame;
public ControlTableResult(CreateTableGUI c){
	this.createFrame=c;
	
}
	
public void valueChanged(ListSelectionEvent listSelectionEvent){
    ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
    if (!(lsm.isSelectionEmpty())) {
    	createFrame.setEnableButtonUpdateDeleteUpDown(true);
    }

}
}
