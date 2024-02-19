<!--Displaying favorite items from store-->
<script setup>
const fav = useFavorite()
const favNo = computed(() => fav.value.length)

</script>

<template>
  <p class="text-4xl mb-10" v-if="favNo > 0">Ulubionych elementów: {{favNo}}</p>
  <p class="text-4xl mb-10" v-else>Brak ulubionych elementów</p>
  <div class="grid grid-cols-4 items-center gap-y-24">
    <ProductListItem v-for="item of fav"
                     :id="item.id"
                     :name="item.name"
                     :price="item.price"
                     :measure-unit="item.measureUnit"
                     :measure-value="item.measureValue"
                     :price-per-measure="item.pricePerMeasure"
                     :key="item.id"
    />
  </div>
</template>