import React, { useEffect, useState } from 'react';
import { api } from '../api.js';
const CATS = ['PIZZA','PATES','ENTREE','DESSERT','BOISSON'];
const vide = { nom:'', description:'', prix:'', categorie:'PIZZA' };
export default function AdminMenu() {
  const [list, setList] = useState([]);
  const [f, setF] = useState(vide);
  const load = () => api.plats().then(setList);
  useEffect(()=>{ load(); }, []);
  async function add(e){ e.preventDefault(); await api.addPlat({...f, prix:+f.prix, disponible:true}); setF(vide); load(); }
  async function del(id){ if(confirm('Supprimer ce plat ?')){ await api.delPlat(id); load(); } }
  return (
    <div>
      <form className="row-form" onSubmit={add}>
        <input placeholder="Nom" value={f.nom} onChange={e=>setF({...f,nom:e.target.value})} required />
        <input placeholder="Description" value={f.description} onChange={e=>setF({...f,description:e.target.value})} style={{minWidth:220}} />
        <select value={f.categorie} onChange={e=>setF({...f,categorie:e.target.value})}>{CATS.map(c=><option key={c}>{c}</option>)}</select>
        <input type="number" placeholder="Prix" value={f.prix} onChange={e=>setF({...f,prix:e.target.value})} style={{width:90}} required />
        <button type="submit">+ Ajouter</button>
      </form>
      <table>
        <thead><tr><th>Plat</th><th>Catégorie</th><th>Prix</th><th>Dispo</th><th></th></tr></thead>
        <tbody>
          {list.map(p=>(
            <tr key={p.id}>
              <td><b>{p.nom}</b><br/><span className="muted">{p.description}</span></td>
              <td><span className="tag">{p.categorie}</span></td>
              <td>{p.prix} MAD</td>
              <td>{p.disponible?<span className="tag ok">Oui</span>:<span className="tag">Non</span>}</td>
              <td><button className="link-danger" onClick={()=>del(p.id)}>Suppr.</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
