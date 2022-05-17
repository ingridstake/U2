import {useEffect, useState } from "react";
import '../styles/searchBar.css';
import axios from 'axios';
import { event } from "./Models";
import React from "react";
import { SearchList } from "./SearchList";
import SearchResultList from "./SearchList";



/**
 * Search for the search parameter and returns a string of matching events
 * @returns a string of matching events
 * getInputValue recives the input from the search bar
 * startSearch sends the search parameter to the backend and return the matching events
 */
export default function SearchBar() {
    
    const [searchParam, setSearchParam] = useState('');
    const [searchResult, setSearchResults] = useState<event[]>([]);
    

    const getInputValue = (e: { target: { value: any; }; }) => {
        const userVal = e.target.value;
        setSearchParam(userVal)        
        console.log(searchParam)
    }

    function searchList() {
        debugger;
        return (
            <SearchResultList events={searchResult} />
        );
    }

    const startSearch = 
        axios.get('http://localhost:8080/search?param=hej'/*+searchParam*/).then(res => {
            const allResults = res.data as event[]
            setSearchResults(allResults)    
            console.log(allResults)
            
        })

    return (
        <section>
            <div className="search-bar">
                <input className="input-text" type="search" placeholder="Sökord..." onChange={(e) => setSearchParam(e.target.value)}  />
            </div>
            <>{searchList}</>
        </section>
    );
}
