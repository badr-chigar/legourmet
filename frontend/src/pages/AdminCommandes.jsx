import React, { useEffect, useState } from 'react';
import { api } from '../api.js';
const FLOW = { RECUE:'EN_PREPARATION', EN_PREPARATION:'SERVIE', SERVIE:'PAYEE' };
const LABEL = { RECUE:'Reçue', EN_PREPARATION:'En préparation', SERVIE:'Servie', PAYEE:'Payée' };
const CLS = { RECUE:'s-planifie', EN_PREPARATION:'s-en_cours', SERVIE:'s-en_cours', PAYEE:'s-termine' };
export default function AdminCommandes() {
  const [list, setList] = useState([]);
  const load = () => api.commandes().then(setList);
  useEffect(()=>{ load(); }, []);
  async function avancer(c){ if (FLOW[c.statut]) { await api.setStatutCmd(c.id, FLOW[c.statut]); load(); } }
  return (
    <table>
      <thead><tr><th>Référence</th><th>Table</th><th>Plats</th><th>Total</th><th>Statut</th><th>Action</th></tr></thead>
      <tbody>
        {list.map(c=>(
          <tr key={c.id}>
            <td>{c.reference}</td><td>{c.tableNumero}</td>
            <td>{c.lignes.map(l=>l.quantite+'× '+l.platNom).join(', ')}</td>
            <td>{c.total} MAD</td>
            <td><span className={'tag '+CLS[c.statut]}>{LABEL[c.statut]}</span></td>
            <td>{FLOW[c.statut] && <button onClick={()=>avancer(c)}>→ {LABEL[FLOW[c.statut]]}</button>}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
