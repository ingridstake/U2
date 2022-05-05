import {useEffect, useState } from "react";
import '../styles/searchBar.css';
import axios from 'axios';

export default function SearchBar() {
    //const [query, setQuery] = useState("");

    const [appState, setAppState] = useState({
        loading: false,
        result: String as any
    });

    const getInputValue = (event: { target: { value: any; }; })=>{
        // show the user input value to console
        const userValue = event.target.value;

        console.log(userValue);
    };
    /*
    axios.post('http://127.0.0.1:8080/search', {
        search: 'p'
      })
      .then((response) => {
        console.log(response);
      }, (error) => {
        console.log(error);
      });
      */

      useEffect(() => {
        setAppState({loading: true, result: ""});
        const apiUrl ='http://127.0.0.1:8080/search';
        axios.get(apiUrl).then(res => {
            const allResults = res.data
            console.log(allResults.target)
            setAppState({ loading: false, result: allResults});
        })
    }, [setAppState])

    /*
    axios.get('http://127.0.0.1:8080/search').then(res => {
        const result = res.data;
        console.log(res.data)
    })
    */

    return (
    <div className="SearchBar">
        <input className="InputText" type="search" placeholder="Vadsomhelst..." onChange={getInputValue}  />
    </div>
    );
}