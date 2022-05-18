/**
 * A type describing an event.
 */
export type event = {
    name: string;
    imageUrl: string;
    date: string;
    city: string;
    shopUri: string;
    infoUri: string;
    e_tags: string[];
}

/**
 * A type describing a category.
 */
export type category = {
    category: string;
    events: event[];
    tags: string[];
}