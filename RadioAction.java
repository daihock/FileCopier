import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioAction implements ActionListener{
    private int copyType;

    /**Set type of main action (copy or move)
     * @param  copyType MainPanel.COPY or MainPanel.MOVE
     */

    public RadioAction(int copyType) {

        this.copyType = copyType;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel.copyType = copyType;

    }

}
