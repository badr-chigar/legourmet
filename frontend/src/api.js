const BASE='/api';
async function req(p,o={}){ const r=await fetch(BASE+p,{headers:{'Content-Type':'application/json'},...o});
  if(!r.ok) throw new Error((await r.json().catch(()=>({}))).error||'Erreur serveur'); return r.status===204?null:r.json(); }
export const api = {
  plats: () => req('/plats'),
  addPlat: (d)=>req('/plats',{method:'POST',body:JSON.stringify(d)}),
  delPlat: (id)=>req('/plats/'+id,{method:'DELETE'}),
  tables: () => req('/tables'),
  reservations: () => req('/reservations'),
  addReservation: (d)=>req('/reservations',{method:'POST',body:JSON.stringify(d)}),
  commandes: () => req('/commandes'),
  addCommande: (d)=>req('/commandes',{method:'POST',body:JSON.stringify(d)}),
  setStatutCmd: (id,statut)=>req('/commandes/'+id+'/statut',{method:'PATCH',body:JSON.stringify({statut})}),
  stats: () => req('/stats'),
};
