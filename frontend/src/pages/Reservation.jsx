import React, { useEffect, useState } from 'react';
import { api } from '../api.js';

export default function Reservation() {
  const [tables, setTables] = useState([]);
  const [f, setF] = useState({ clientNom:'', telephone:'', date:'', heure:'20:00', couverts:2, tableId:'' });
  const [ok, setOk] = useState(false);
  const [err, setErr] = useState('');
  useEffect(()=>{ api.tables().then(setTables).catch(()=>{}); }, []);
  async function submit(e){
    e.preventDefault(); setErr('');
    try {
      await api.addReservation({ clientNom:f.clientNom, telephone:f.telephone, date:f.date, heure:f.heure,
        couverts:+f.couverts, table: f.tableId?{id:+f.tableId}:null });
      setOk(true);
    } catch(e){ setErr(e.message); }
  }
  if (ok) return <div className="confirm"><div className="check">✓</div><h2>Réservation confirmée</h2><p>Merci {f.clientNom}, à bientôt au Gourmet !</p></div>;
  return (
    <div className="resa">
      <h1>Réserver une table</h1>
      <form className="resa-form" onSubmit={submit}>
        <label>Nom complet<input required value={f.clientNom} onChange={e=>setF({...f,clientNom:e.target.value})} /></label>
        <label>Téléphone<input required value={f.telephone} onChange={e=>setF({...f,telephone:e.target.value})} /></label>
        <div className="r2">
          <label>Date<input type="date" required value={f.date} onChange={e=>setF({...f,date:e.target.value})} /></label>
          <label>Heure<input type="time" required value={f.heure} onChange={e=>setF({...f,heure:e.target.value})} /></label>
        </div>
        <div className="r2">
          <label>Couverts<input type="number" min="1" value={f.couverts} onChange={e=>setF({...f,couverts:e.target.value})} /></label>
          <label>Table<select value={f.tableId} onChange={e=>setF({...f,tableId:e.target.value})}>
            <option value="">Sans préférence</option>
            {tables.map(t=><option key={t.id} value={t.id}>{t.numero} · {t.couverts} couv. · {t.zone}</option>)}
          </select></label>
        </div>
        {err && <div className="error">{err}</div>}
        <button className="btn-primary full" type="submit">Confirmer la réservation</button>
      </form>
    </div>
  );
}
