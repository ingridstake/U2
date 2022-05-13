import {useEffect, useState } from "react";
import '../styles/searchBar.css';
import axios from 'axios';

/**
 * Search for the search parameter and returns a string of matching events
 * @returns a string of matching events
 * getInputValue recives the input from the search bar
 * startSearch sends the search parameter to the backend and return the matching events
 */
export default function SearchBar() {
    
    const [searchParam, setSearchParam] = useState('');

    const getInputValue = (e: { target: { value: any; }; }) => {
        const userVal = e.target.value;
        setSearchParam(userVal)        
        console.log(searchParam)
    }
      
    const startSearch = 
        axios.get('http://localhost:8080/search?param='+searchParam).then(res => {
            const allResults = res.data
            console.log(allResults)
        })

    return (
    <div className="search-bar">
        <input className="input-text" type="search" placeholder="Sökord..." onChange={getInputValue}  />
    </div>
    );
}