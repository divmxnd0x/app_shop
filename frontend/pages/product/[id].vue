<!--Page to represent single product.-->
<!--Remember to check that option you want to display is present in fetch response -->
<script setup>
const route = useRoute()
const router = useRouter()
const utils = useUtils()
const fav = useFavorite()
const id = route.params.id

const response = await useFetch('http://localhost:8080/api/products/product',
    {
      method: 'GET',
      params: {
        id: id
      }
    })

const data = response.data;
const isInFav = ref(utils.isInFavorite(id, fav.value))

watch(fav, () => {
  isInFav.value = utils.isInFavorite(id, fav.value)
})

const handleButtonClick = () => {
  if (!isInFav.value) {
    fav.value = [
      ...fav.value,
      {
        id: data.value.id,
        name: data.value.name,
        discountPrice: data.value.discountPrice,
        price: data.value.price,
        measureUnit: data.value.measure.unit,
        measureValue: data.value.measure.value,
        pricePerMeasure: utils.calculatePricePerMeasure(Number(data.value.measure.value), Number(data.value.price))
      }
    ]
  } else {
    fav.value = fav.value.filter(item => item.id !== id)
  }
  console.log(fav.value)
}

</script>

<template>
  <div>
    <div class="flex justify-center">
      <img :src="`http://localhost:8080/api/products/image?id=${data.id}`" alt="image"/>
      <div id="near-image" class="flex flex-col justify-between items-center">
        <p class="text-2xl px-2">{{ data.name }}</p>
        <p class="text-2xl px-2">Dostępna ilość: {{ data.availableCount }}</p>
        <p class="text-2xl text-wrap px-2">
          <span v-if="data.discountPrice" class="text-red-600">{{ data.discountPrice }} zł</span>
          <span v-if="data.discountPrice" class="line-through">{{ data.price }} zł</span>
          <span v-else>{{ data.price }} zł</span>
        </p>
        <ClientOnly>
          <UButton
              :color="isInFav ? 'red' : 'primary'"
              @click="handleButtonClick"
          >
            {{ isInFav ? 'Usuń z ulubionych' : 'Dodaj do ulubionych' }}
          </UButton>
        </ClientOnly>
      </div>
    </div>
    <div v-if="data.description" class="my-20">
      <p class="text-4xl">Opis</p>
      <p>
        {{ data.description }}
      </p>
    </div>
    <div v-if="data.ingredients" class="my-20">
      <p class="text-4xl">Składniki</p>
      <p>
        {{ data.ingredients }}
      </p>
    </div>
    <div v-if="data.producer" class="my-20">
      <p class="text-4xl">Producent</p>
      <p>
        {{ data.producer }}
      </p>
    </div>
    <div v-if="data.storingConditions" class="my-20">
      <p class="text-4xl">Warunki przechowywania</p>
      <p>
        {{ data.storingConditions }}
      </p>
    </div>
    <div v-if="data.usageInstruction" class="my-20">
      <p class="text-4xl">Instrukcja użycia</p>
      <p>
        {{ data.usageInstruction }}
      </p>
    </div>
    <div v-if="data.otherInformation">
      <p class="text-4xl">Dodatkowe informacje</p>
      <ul>
        <li v-for="info in data.otherInformation">{{ info }}</li>
      </ul>
    </div>
    <div v-if="data.recommendedDailyDose" class="my-20">
      <p class="text-4xl">Zalecana dzienna porcja</p>
      <UTable :rows="[data.recommendedDailyDose]" :columns="utils.createDailyDoseColumns(data.recommendedDailyDose)"/>
    </div>

  </div>
</template>

<style scoped>
img {
  width: 370px;
  height: 258px;
  display: block;
}

#near-image {
  height: 258px;
}
</style>