import type {Favorite} from "~/composables/store";

export const useUtils =() => {
    const pricePerMeasureText = (unit: string, pricePerMeasure: string): string => {
        if (!unit || !pricePerMeasure) {
            return ''
        }
        return `(${pricePerMeasure} / ${unit == 'GRAM' ? 'kg' : 'l'})`
    }

    const createDailyDoseColumns = (recomendedDailyDose: any) : any => {
        const createColumn = (key: string, label: string) => { return {key: key, label: label}}
        const columns = []
        if (recomendedDailyDose.energy) {
            columns.push(createColumn('energy', 'WARTOŚĆ ENERGETYCZNA [Kcal]'))
        }
        if (recomendedDailyDose.fat) {
            columns.push(createColumn('fat', 'TŁUSZCZE [g]'))
        }
        if (recomendedDailyDose.saturatedFattyAcids) {
            columns.push(createColumn('saturatedFattyAcids', 'NASYCONE KWASY TŁUSZCZOWE [g]'))
        }
        if (recomendedDailyDose.carbs) {
            columns.push(createColumn('carbs', 'WĘGLOWODANY [g]'))
        }
        if (recomendedDailyDose.sugar) {
            columns.push(createColumn('sugar', 'CUKIER [g]'))
        }
        if (recomendedDailyDose.proteins) {
            columns.push(createColumn('proteins', 'BIAŁKA [g]'))
        }
        if (recomendedDailyDose.salt) {
            columns.push(createColumn('salt', 'SÓl [g]'))
        }
        return columns;
    }

    const getAvailableCategories = () : Category[] => [
        {
            name: 'vegetables',
            displayName: 'Warzywa'
        },
        {
            name: 'rice',
            displayName: 'Ryż'
        },
        {
            name: 'fruits',
            displayName: 'Owoce'
        },
        {
            name: 'meat',
            displayName: 'Mięso'
        },
        {
            name: 'cheese',
            displayName: 'Ser'
        },
        {
            name: 'milk',
            displayName: 'Mleko'
        },
        {
            name: 'bread',
            displayName: 'Pieczywo'
        },
        {
            name: 'pasta',
            displayName: 'Makarony'
        },
        {
            name: 'salt_and_sugar',
            displayName: 'Sól i cukier'
        }
    ]

    const getCategoryDisplayName = (key: string): string | null => {
        return getAvailableCategories()
            .find(cat => cat.name === key)
            .displayName
    }

    const isInFavorite = (id: string, fav: Favorite[]) : boolean => {
        return fav.filter(item => item.id === id).length > 0
    }

    const calculatePricePerMeasure = (measureValue: number, price: number): number => {
        let value = (1000.0 / measureValue) * price;
        return Math.round(value * 100) / 100;
    }

    return {
        pricePerMeasureText,
        createDailyDoseColumns,
        getAvailableCategories,
        getCategoryDisplayName,
        isInFavorite,
        calculatePricePerMeasure
    }
}

export interface Category {
    name: string,
    displayName: string
}