//
// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL version '3.1.0'.
//
package com.presidium.smashtourney.dao.adapter;

import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.ListAdapter;
import com.apollographql.apollo3.api.NullableAdapter;
import com.apollographql.apollo3.api.ObjectAdapter;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.presidium.smashtourney.dao.TournamentsByVideogameQuery;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TournamentsByVideogameQuery_ResponseAdapter {
  public enum Data implements Adapter<TournamentsByVideogameQuery.Data> {
    INSTANCE;

    private static final List<String> RESPONSE_NAMES = Arrays.asList("tournaments");

    @Override
    public TournamentsByVideogameQuery.Data fromJson(JsonReader reader,
													 CustomScalarAdapters customScalarAdapters) throws IOException {
      TournamentsByVideogameQuery.Tournaments tournaments = null;

      loop:
      while(true) {
        switch (reader.selectName(RESPONSE_NAMES)) {
          case 0: tournaments = new NullableAdapter<>(new ObjectAdapter<TournamentsByVideogameQuery.Tournaments>(Tournaments.INSTANCE, false)).fromJson(reader, customScalarAdapters); break;
          default: break loop;
        }
      }

      return new TournamentsByVideogameQuery.Data(
        tournaments
      );
    }

    @Override
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters,
        TournamentsByVideogameQuery.Data value) throws IOException {
      writer.name("tournaments");
      new NullableAdapter<>(new ObjectAdapter<TournamentsByVideogameQuery.Tournaments>(Tournaments.INSTANCE, false)).toJson(writer, customScalarAdapters, value.tournaments);
    }
  }

  public enum Tournaments implements Adapter<TournamentsByVideogameQuery.Tournaments> {
    INSTANCE;

    private static final List<String> RESPONSE_NAMES = Arrays.asList("nodes");

    @Override
    public TournamentsByVideogameQuery.Tournaments fromJson(JsonReader reader,
															CustomScalarAdapters customScalarAdapters) throws IOException {
      List<TournamentsByVideogameQuery.Node> nodes = null;

      loop:
      while(true) {
        switch (reader.selectName(RESPONSE_NAMES)) {
          case 0: nodes = new NullableAdapter<>(new ListAdapter<>(new NullableAdapter<>(new ObjectAdapter<TournamentsByVideogameQuery.Node>(Node.INSTANCE, false)))).fromJson(reader, customScalarAdapters); break;
          default: break loop;
        }
      }

      return new TournamentsByVideogameQuery.Tournaments(
        nodes
      );
    }

    @Override
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters,
        TournamentsByVideogameQuery.Tournaments value) throws IOException {
      writer.name("nodes");
      new NullableAdapter<>(new ListAdapter<>(new NullableAdapter<>(new ObjectAdapter<TournamentsByVideogameQuery.Node>(Node.INSTANCE, false)))).toJson(writer, customScalarAdapters, value.nodes);
    }
  }

  public enum Node implements Adapter<TournamentsByVideogameQuery.Node> {
    INSTANCE;

    private static final List<String> RESPONSE_NAMES = Arrays.asList("id", "name", "slug");

    @Override
    public TournamentsByVideogameQuery.Node fromJson(JsonReader reader,
													 CustomScalarAdapters customScalarAdapters) throws IOException {
      String id = null;
      String name = null;
      String slug = null;

      loop:
      while(true) {
        switch (reader.selectName(RESPONSE_NAMES)) {
          case 0: id = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters); break;
          case 1: name = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters); break;
          case 2: slug = Adapters.NullableStringAdapter.fromJson(reader, customScalarAdapters); break;
          default: break loop;
        }
      }

      return new TournamentsByVideogameQuery.Node(
        id,
        name,
        slug
      );
    }

    @Override
    public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters,
        TournamentsByVideogameQuery.Node value) throws IOException {
      writer.name("id");
      Adapters.NullableStringAdapter.toJson(writer, customScalarAdapters, value.id);

      writer.name("name");
      Adapters.NullableStringAdapter.toJson(writer, customScalarAdapters, value.name);

      writer.name("slug");
      Adapters.NullableStringAdapter.toJson(writer, customScalarAdapters, value.slug);
    }
  }
}
