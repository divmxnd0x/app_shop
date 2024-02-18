export const useFavorite = () => useState<Favorite[]>('favorite', () => [])
export interface Favorite {
    id: string,
    name: string,
    price: number,
    discountPrice: number | null,
    measureUnit: string,
    measureValue: number,
    pricePerMeasure: number,
}