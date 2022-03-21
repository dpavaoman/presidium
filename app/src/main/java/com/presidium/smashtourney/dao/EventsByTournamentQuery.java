//
// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL version '3.1.0'.
//
package com.presidium.smashtourney.dao;

import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.CompiledField;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.ObjectAdapter;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.presidium.smashtourney.dao.adapter.EventsByTournamentQuery_ResponseAdapter;
import com.presidium.smashtourney.dao.adapter.EventsByTournamentQuery_VariablesAdapter;
import com.presidium.smashtourney.dao.selections.EventsByTournamentQuerySelections;

import java.io.IOException;
import java.util.List;

public class EventsByTournamentQuery implements Query<EventsByTournamentQuery.Data> {
  public static final String OPERATION_ID = "4badcd584dac6d2862f84b494a254c1d5f23570f5bac953bb1a8ae799bf37d8c";

  /**
   * The minimized GraphQL document being sent to the server to save a few bytes.
   * The un-minimized version is:
   *
   * query EventsByTournament($tournamentId: ID!) {
   *   tournament(id: $tournamentId) {
   *     id
   *     events {
   *       name
   *       videogame {
   *         id
   *       }
   *       id
   *     }
   *   }
   * }
   */
  public static final String OPERATION_DOCUMENT = "query EventsByTournament($tournamentId: ID!) { tournament(id: $tournamentId) { id events { name videogame { id } id } } }";

  public static final String OPERATION_NAME = "EventsByTournament";

  public final String tournamentId;

  private transient volatile int $hashCode;

  private transient volatile boolean $hashCodeMemoized;

  private transient volatile String $toString;

  public EventsByTournamentQuery(String tournamentId) {
    this.tournamentId = tournamentId;
  }

  public EventsByTournamentQuery() {
    this.tournamentId = "tournamentId";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof EventsByTournamentQuery) {
      EventsByTournamentQuery that = (EventsByTournamentQuery) o;
      return ((this.tournamentId == null) ? (that.tournamentId == null) : this.tournamentId.equals(that.tournamentId));
    }
    return false;
  }

  @Override
  public int hashCode() {
    if (!$hashCodeMemoized) {
      int h = 1;
      h *= 1000003;
      h ^= (tournamentId == null) ? 0 : tournamentId.hashCode();
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = OPERATION_DOCUMENT;
    }
    return $toString;
  }

  @Override
  public String id() {
    return OPERATION_ID;
  }

  @Override
  public String document() {
    return OPERATION_DOCUMENT;
  }

  @Override
  public String name() {
    return OPERATION_NAME;
  }

  @Override
  public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters)
      throws IOException {
    EventsByTournamentQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
  }

  @Override
  public Adapter<Data> adapter() {
    return new ObjectAdapter<Data>(EventsByTournamentQuery_ResponseAdapter.Data.INSTANCE, false);
  }

  @Override
  public CompiledField rootField() {
    return new CompiledField.Builder(
      "data",
      com.presidium.smashtourney.dao.type.Query.type
    )
    .selections(EventsByTournamentQuerySelections.root)
    .build();
  }

  public static class Data implements Query.Data {
    /**
     * Returns a tournament given its id or slug
     */
    public Tournament tournament;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    private transient volatile String $toString;

    public Data(Tournament tournament) {
      this.tournament = tournament;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return ((this.tournament == null) ? (that.tournament == null) : this.tournament.equals(that.tournament));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (tournament == null) ? 0 : tournament.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "tournament=" + tournament
          + "}";
      }
      return $toString;
    }
  }

  public static class Tournament {
    public String id;

    public List<Event> events;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    private transient volatile String $toString;

    public Tournament(String id, List<Event> events) {
      this.id = id;
      this.events = events;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Tournament) {
        Tournament that = (Tournament) o;
        return ((this.id == null) ? (that.id == null) : this.id.equals(that.id))
         &&((this.events == null) ? (that.events == null) : this.events.equals(that.events));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (id == null) ? 0 : id.hashCode();
        h *= 1000003;
        h ^= (events == null) ? 0 : events.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Tournament{"
          + "id=" + id + ", "
          + "events=" + events
          + "}";
      }
      return $toString;
    }
  }

  public static class Event {
    /**
     * Title of event set by organizer
     */
    public String name;

    public Videogame videogame;

    public String id;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    private transient volatile String $toString;

    public Event(String name, Videogame videogame, String id) {
      this.name = name;
      this.videogame = videogame;
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Event) {
        Event that = (Event) o;
        return ((this.name == null) ? (that.name == null) : this.name.equals(that.name))
         &&((this.videogame == null) ? (that.videogame == null) : this.videogame.equals(that.videogame))
         &&((this.id == null) ? (that.id == null) : this.id.equals(that.id));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (name == null) ? 0 : name.hashCode();
        h *= 1000003;
        h ^= (videogame == null) ? 0 : videogame.hashCode();
        h *= 1000003;
        h ^= (id == null) ? 0 : id.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Event{"
          + "name=" + name + ", "
          + "videogame=" + videogame + ", "
          + "id=" + id
          + "}";
      }
      return $toString;
    }
  }

  public static class Videogame {
    public String id;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    private transient volatile String $toString;

    public Videogame(String id) {
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Videogame) {
        Videogame that = (Videogame) o;
        return ((this.id == null) ? (that.id == null) : this.id.equals(that.id));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (id == null) ? 0 : id.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Videogame{"
          + "id=" + id
          + "}";
      }
      return $toString;
    }
  }
}
