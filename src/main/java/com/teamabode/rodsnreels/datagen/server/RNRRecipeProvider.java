package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RNRRecipeProvider extends FabricRecipeProvider {
    public RNRRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static <T extends AbstractCookingRecipe> void offerFoodCookingRecipe(RecipeExporter exporter, String process, RecipeSerializer<T> serializer, AbstractCookingRecipe.RecipeFactory<T> factory, int cookingTime, ItemConvertible input, ItemConvertible output, float experience) {
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), RecipeCategory.FOOD, output, experience, cookingTime, serializer, factory).criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input)).offerTo(exporter, RodsNReels.id(RecipeProvider.getItemPath(output) + "_from_" + process));
    }

    @Override
    public void generate(RecipeExporter exporter) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(RNRItems.SQUID), RecipeCategory.FOOD, RNRItems.COOKED_SQUID, 0.35F, 200).criterion(hasItem(RNRItems.SQUID), conditionsFromItem(RNRItems.SQUID)).offerTo(exporter);
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, RNRItems.SQUID, RNRItems.COOKED_SQUID, 0.35F);
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new, 600, RNRItems.SQUID, RNRItems.COOKED_SQUID, 0.35F);
    }
}
