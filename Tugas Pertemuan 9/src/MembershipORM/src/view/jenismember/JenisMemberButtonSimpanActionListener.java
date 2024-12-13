/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MembershipORM.src.view.jenismember;
/*@author HUMAIRA*/
import MembershipORM.src.dao.JenisMemberDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import MembershipORM.src.model.JenisMember;

public class JenisMemberButtonSimpanActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame,
            JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getNama();
        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);
        this.jenisMemberFrame.addJenisMember(jenisMember);
        this.jenisMemberDao.insert(jenisMember);
    }
}
