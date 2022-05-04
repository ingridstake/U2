import { useState } from "react";
import '../styles/searchBar.css';

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