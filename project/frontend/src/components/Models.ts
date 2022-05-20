import internal from "stream";
import { Url } from "url";

/**
 * A type describing an event.
 */
export type event = {
    name: string;
    imageUrl: string;
    date: string;
    city: string;
    venue: string;
    shopUri: string;
    infoUri: string;
}

/**
 * A type describing a category.
 */
export type category = {
    category: string;
    events: event[];
    tags: string[];
}