import {useEffect, useState } from "react";
import '../styles/searchBar.css';
import axios from 'axios';

export default function SearchBar() {

    // working!!  Nu funkasr det
    /*
    useEffect(()=> {
        fetch('http://127.0.0.1:8080/search').then(resp=>resp.text())
        .then(resp => {
            console.log('eventsting from api: ' + resp)
        })
    }, [])
    */
    const [appState, setAppState] = useState({
        loading: false,
        result: String as any
    });

    useEffect(() => {
        setAppState({loading: true, result: ""});
        const apiUrl ='http://127.0.0.1:8080/search';
        axios.get(apiUrl).then(res => {
            const allResults = res.data
            console.log(allResults)
            setAppState({ loading: false, result: allResults});
        })
    }, [setAppState])
    /*
    return (
    <div className="SearchBar">
        <input className="InputText" type="search" placeholder="Sökord..." onChange={getInputValue}  />
    </div>
    );
    */
    return (
        <div className="SearchBar">
            <input className="InputText" type="search" placeholder="Sökord..." />
        </div>
        );
}