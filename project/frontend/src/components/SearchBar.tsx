import { useState } from "react";
import '../styles/searchBar.css';

//import THANK_U_NEXT from "./THANK_U_NEXT";
//import "./styles.css";
/*
const getFilteredItems = (query, items) => {
  if (!query) {
    return items;
  }
  return items.filter((song) => song.name.includes(query));
};
*/
/*
export default function App() {
  const [query, setQuery] = useState("");

  const { tracks } = THANK_U_NEXT;
  const { items } = tracks;
  // items looks like this: [{name: 'name1'}, {name: 'name2'}]

  const filteredItems = getFilteredItems(query, items);

  return (
    <div className="App">
      <label>Search</label>
      <input type="text" onChange={(e) => setQuery(e.target.value)} />
      <ul>
        {filteredItems.map((value) => (
          <h1 key={value.name}>{value.name}</h1>
        ))}
      </ul>
    </div>
  );
}
*/

export default function SearchBar() {
    const [query, setQuery] = useState("");

    const getInputValue = (event: { target: { value: any; }; })=>{
        // show the user input value to console
        const userValue = event.target.value;

        console.log(userValue);
    };


    return (
    <div className="SearchBar">
        <input className="InputText" type="search" placeholder="Vadsomhelst..." onChange={getInputValue}  />
    </div>

    );
}